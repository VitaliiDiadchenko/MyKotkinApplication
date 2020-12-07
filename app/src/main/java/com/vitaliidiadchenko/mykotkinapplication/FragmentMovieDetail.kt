package com.vitaliidiadchenko.mykotkinapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitaliidiadchenko.mykotkinapplication.adapter.ActorViewHolderAdapter
import com.vitaliidiadchenko.mykotkinapplication.data.Actor

class FragmentMovieDetail : Fragment() {

    private var listener: FragmentListener? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        view?.findViewById<Button>(R.id.button_back)?.apply {
            setOnClickListener{
                listener?.goToFragmentMoviesList()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view_list_actors)
        recyclerView?.adapter = ActorViewHolderAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView?.hasFixedSize()
        updateData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FragmentListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun updateData() {
        (recyclerView?.adapter as? ActorViewHolderAdapter)?.apply {
            bindActors(listActors)
        }
    }

    companion object{
        private val downey = Actor(R.drawable.img_downey, "Robert Downey Jr.")
        private val evans = Actor(R.drawable.img_evans, "Chris Evans")
        private val ruffalo = Actor(R.drawable.img_ruffalo, "Mark Ruffalo")
        private val hemsworth = Actor(R.drawable.img_hemsworth, "Chris Hemsworth")
        private val listActors = listOf(downey, evans, ruffalo, hemsworth)
    }

}