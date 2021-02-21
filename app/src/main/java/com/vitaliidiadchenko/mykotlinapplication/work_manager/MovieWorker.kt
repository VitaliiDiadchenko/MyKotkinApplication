package com.vitaliidiadchenko.mykotlinapplication.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitaliidiadchenko.mykotlinapplication.data.db.repository.RepositoryHolder
import com.vitaliidiadchenko.mykotlinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotlinapplication.network_module.RetrofitHolder
import com.vitaliidiadchenko.mykotlinapplication.network_module.dto.moviesDtoMapping
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

class MovieWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val movieNotification by lazy { BuildNotification(context = context) }

    @ExperimentalSerializationApi
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val movieApi = RetrofitHolder.retrofit.create<MovieApi>()
                val repository = RepositoryHolder.createRepository()
                val moviesDto = movieApi.getMovies()
                val genersDto = movieApi.getGenres()
                val movies = moviesDtoMapping(moviesDto.results, genersDto.genres)
                if (movies.isNotEmpty()) {
                    repository.updateMoviesIntoDb(movies)
                }
                val movie = repository.getMovieWithMaxRating()
                movieNotification.showNotification(movie)
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}