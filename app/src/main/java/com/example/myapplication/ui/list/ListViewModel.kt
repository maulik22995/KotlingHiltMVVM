package com.example.myapplication.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.PressReleaseDoc
import com.example.myapplication.data.services.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {
    var liveDataPressRelease: MutableLiveData<PressReleaseDoc> =
        MutableLiveData<PressReleaseDoc>()

    fun fetchAllData() {
        val apiCall: Call<PressReleaseDoc> = apiInterface.getPressRelease()
        apiCall.enqueue(object : Callback<PressReleaseDoc> {
            override fun onResponse(
                call: Call<PressReleaseDoc>,
                response: Response<PressReleaseDoc>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null) liveDataPressRelease.value = response.body()
                }
            }

            override fun onFailure(call: Call<PressReleaseDoc?>, t: Throwable) {}
        })
    }
}