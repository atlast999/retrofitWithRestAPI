
package com.example.android.marsrealestate.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarApiFilter
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {
    private val TAG = "OverviewViewModel"
    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus>
        get() = _apiStatus

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _clickedProperty = MutableLiveData<MarsProperty>()
    val clickedProperty: LiveData<MarsProperty>
        get() = _clickedProperty

    val adapter = PhotoGridAdapter{ _clickedProperty.value = it }

    init {
        getMarsRealEstateProperties(MarApiFilter.SHOW_ALL)
    }

    fun updateFilter(filter: MarApiFilter){
        getMarsRealEstateProperties(filter)
        Log.d(TAG, "updateFilter: ${filter.value}")
    }

    private fun getMarsRealEstateProperties(filter: MarApiFilter) {
        viewModelScope.launch {
            _apiStatus.value = ApiStatus.LOADING
            try {
                _properties.value = MarsApi.marsService.getProperties(filter.value)
                Log.d(TAG, "getMarsRealEstateProperties: ${_properties.value!!.any { it.isRental }}")
                _apiStatus.value = ApiStatus.DONE
            }catch (e: Exception){
                _apiStatus.value = ApiStatus.ERROR
                _properties.value = emptyList()
            }
        }
    }
}

enum class ApiStatus{
    LOADING,
    DONE,
    ERROR
}


