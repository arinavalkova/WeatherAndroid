package com.nsu.ccfit.weatherapplication.presentation

class LiveEvent : SingleLiveEvent<Unit>() {
	
	operator fun invoke() {
		this.value = Unit
	}
}