package com.example.anteeoneapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.model.Todo
import com.example.anteeoneapp.ui.MainViewModel
import com.example.anteeoneapp.ui.objects.TodoAdapter

import kotlinx.android.synthetic.main.fragment_main.add_todo

class MainFragment : Fragment() {

    private var mContext: Context? = null
    private var mRecyclerView: RecyclerView? = null
    private var mViewModel : MainViewModel? = null
    private var mAdapter : TodoAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        initMembers()
        initRecycler()
        initView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun initMembers(){
        mRecyclerView = view?.findViewById(R.id.todo_list)
    }

    private fun initRecycler(){
        mRecyclerView?.run {
            mAdapter = TodoAdapter()
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        mViewModel = ViewModelProvider(this,
            ViewModelProvider
                .AndroidViewModelFactory(App.getInstance()))
                .get(MainViewModel::class.java)
        mViewModel?.liveData?.observe(this,object: Observer<List<Todo>>{
            override fun onChanged(todos: List<Todo>) {
                mAdapter?.setItems(todos)
            }
        })
    }

    private fun initView(){
        add_todo.setOnClickListener{
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container,DetailFragment())
                ?.addToBackStack("detail")
                ?.commit()
        }
    }





}