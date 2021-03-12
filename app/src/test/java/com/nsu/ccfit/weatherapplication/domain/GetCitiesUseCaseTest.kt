package com.nsu.ccfit.weatherapplication.domain

import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Test

class GetCitiesUseCaseTest {
    private val cityRepository: CityRepository = mockk()

    private val getCitiesUseCase = GetCitiesUseCase(cityRepository)

    @Test
    fun `get people EXPECT list of person`() {
        every { cityRepository.getCities() } returns listOf(String())

        val cities = getCitiesUseCase()

        assertEquals(listOf(String()), cities)
    }
}