package com.nsu.ccfit.weatherapplication.domain

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import org.junit.Assert.assertEquals
import org.junit.Test

class CreateCityUseCaseTest {

    private val cityRepository: CityRepository = mockk()
    private val maybeCity: Maybe<City> = mockk()
    private val createCityUseCase = CreateCityUseCase(cityRepository)

    @Test
    fun `create city by name EXPECT city`() {
        every { cityRepository.createCity(String()) } returns maybeCity

        val city = createCityUseCase(String())

        assertEquals(maybeCity, city)
    }
}