package com.example.anteeoneapp.ui.fragments

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.model.Todo

import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()

    }

    private fun initView(){
        buttonTodoCommit.setOnClickListener{
            val todo = Todo(
                null,
                editTodoTitle.text.toString(),
                editTodoDescription.text.toString(),
                System.currentTimeMillis()
            )
            App.getInstance().todoDao.insert(todo)
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container,MainFragment())
                ?.commit()
        }
    }


}