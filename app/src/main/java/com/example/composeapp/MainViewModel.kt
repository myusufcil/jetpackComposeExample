package com.example.composeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.response.Recommendations
import kotlinx.coroutines.launch

private const val TAG = "User View Model"
class MainViewModel : ViewModel() {

    private var _recommendation = MutableLiveData<Recommendations>()
    val recommendations: LiveData<Recommendations>
        get() = _recommendation

    fun getData() {
        viewModelScope.launch {
            try {
                _recommendation.value = RetrofitInstance.retrofitInstance.getUserData()
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
    }
}