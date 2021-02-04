package com.vitaliidiadchenko.mykotkinapplication.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitaliidiadchenko.mykotkinapplication.data.db.repository.RepositoryHolder
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotkinapplication.network_module.RetrofitHolder
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.moviesDtoMapping
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

class MovieWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @ExperimentalSerializationApi
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val movieApi = RetrofitHolder.retrofit.create<MovieApi>()
                val repository = RepositoryHolder.createRepository()
                val moviesDto = movieApi.getMovies()
                val genersDto = movieApi.getGenres()
                val movies = moviesDtoMapping(moviesDto.result, genersDto.genres)
                if(movies.isNotEmpty()) repository.updateMoviesIntoDb(movies)
                Result.success()
            } catch(e: Exception) {
                Result.failure()
            }
        }
    }
}