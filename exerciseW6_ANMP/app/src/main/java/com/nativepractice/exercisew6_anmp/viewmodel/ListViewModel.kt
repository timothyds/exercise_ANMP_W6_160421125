package com.nativepractice.exercisew6_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nativepractice.exercisew6_anmp.model.Motor

class ListViewModel(application: Application): AndroidViewModel(application) {
    val motorLD = MutableLiveData<ArrayList<Motor>>()
    val motorLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        Log.d("CEKMASUK","masukvolley")
        loadingLD.value = true
        motorLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/motor/motor.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Motor>>() { }.type
                val result = Gson().fromJson<List<Motor>>(it, sType)
                motorLD.value = result as ArrayList<Motor>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                motorLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}