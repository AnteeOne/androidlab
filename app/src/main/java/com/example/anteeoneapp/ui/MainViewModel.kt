package com.example.anteeoneapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.anteeoneapp.App
import com.example.anteeoneapp.model.Todo

class MainViewModel : ViewModel() {

    var liveData: LiveData<List<Todo>>

    init {
        liveData = App.getInstance().todoDao.getAllLiveData()
    }
}