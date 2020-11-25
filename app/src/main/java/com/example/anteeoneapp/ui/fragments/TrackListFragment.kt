package com.example.anteeoneapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.repository.TrackRepository
import com.example.anteeoneapp.ui.objects.TrackListAdapter

class TrackListFragment : BaseFragment(R.layout.fragment_track_list) {

    private lateinit var mRecyclerView: RecyclerView

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()

    }

    private fun initFunc() {
        with(mRecyclerView) {

            adapter = TrackListAdapter(TrackRepository.tracksList) {id:Int ->
                var args = Bundle().also {
                    it.putInt("id",id)
                }
                fragmentManager?.beginTransaction()
                    ?.addToBackStack(null)
                    ?.replace(R.id.fragment_container, TrackDetailFragment().apply {
                        arguments = args
                    })
                    ?.commit()

            }
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }


    }

    private fun initFields() {
        mRecyclerView = mRootView.findViewById(R.id.fragment_track_list_rvlist)
    }
}