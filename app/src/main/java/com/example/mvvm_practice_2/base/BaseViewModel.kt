package com.example.mvvm_practice_2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loadingBool = MutableLiveData<Boolean>()

    fun isLoading(): LiveData<Boolean> {
        return loadingBool
    }
}