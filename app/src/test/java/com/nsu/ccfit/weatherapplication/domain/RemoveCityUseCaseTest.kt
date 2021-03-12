package com.nsu.ccfit.weatherapplication.domain

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoveCityUseCaseTest {

    private val cityRepository: CityRepository = mockk()
    private val completable: Completable = mockk()
    private val removeCityUseCase = RemoveCityUseCase(cityRepository)

    @Test
    fun `remove city by name EXPECT completable`() {
        every { cityRepository.deleteCity(String()) } returns completable

        val gottenAnswer = removeCityUseCase(String())

        assertEquals(completable, gottenAnswer)
    }
}