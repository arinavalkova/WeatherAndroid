package com.nsu.ccfit.weatherapplication.domain

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import junit.framework.Assert.assertEquals
import org.junit.Test

class GetCityUseCaseTest {

    private val cityRepository: CityRepository = mockk()
    private val maybeCity: Maybe<City> = mockk()
    private val getCityUseCase = GetCityUseCase(cityRepository)

    @Test
    fun `get city EXPECT city`() {
        every { cityRepository.getCity(String()) } returns maybeCity

        val city = getCityUseCase(String())

        assertEquals(maybeCity, city)
    }
}