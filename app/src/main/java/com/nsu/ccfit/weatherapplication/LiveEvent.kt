package com.nsu.ccfit.weatherapplication

class LiveEvent : SingleLiveEvent<Unit>() {
	
	operator fun invoke() {
		this.value = Unit
	}
}