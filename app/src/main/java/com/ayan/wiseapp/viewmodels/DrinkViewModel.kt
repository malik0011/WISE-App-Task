package com.ayan.wiseapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayan.wiseapp.models.Drink
import com.ayan.wiseapp.services.RetrofitBuilder

class DrinkViewModel : ViewModel() {

    private val apiService = RetrofitBuilder.apiService

    //for drinks
    private val _drinksLiveData = MutableLiveData<List<Drink>>()
    val drinksLiveData: LiveData<List<Drink>> get() = _drinksLiveData

    //for toast
    private val _toastLiveData = MutableLiveData<String>()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    suspend fun fetchData(searchString: String) {
        try {
            val responseData = apiService.searchCocktailByName(searchString)
            if (responseData.body() != null) {
                _drinksLiveData.postValue(responseData.body()!!.drinks)
            } else {
                //have to show an toast
                _toastLiveData.postValue("error: response body is null!")
            }
        } catch (e: Exception) {
            _toastLiveData.postValue("error: Please your check internet connection!")
        }
    }
}