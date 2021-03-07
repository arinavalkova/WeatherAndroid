package com.nsu.ccfit.weatherapplication.presentation.create

import androidx.lifecycle.MutableLiveData
import com.nsu.ccfit.weatherapplication.domain.CreateCityUseCase
import com.nsu.ccfit.weatherapplication.presentation.BaseViewModel
import com.nsu.ccfit.weatherapplication.presentation.LiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class CreateViewModel(
    private val createCityUseCase: CreateCityUseCase
) : BaseViewModel() {

    val closeScreenEvent = LiveEvent()
    val loading = MutableLiveData(false)
    val toast = MutableLiveData<String>()

    fun createCity(name: String) {
        loading.value = true
        createCityUseCase(name)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                loading.value = false
            }
            .subscribe({
                toast.value = "City was found"
                closeScreenEvent()
            }, {
                toast.value = "City wasn't found"
                closeScreenEvent()
            })
            .untilDestroy()
    }
}