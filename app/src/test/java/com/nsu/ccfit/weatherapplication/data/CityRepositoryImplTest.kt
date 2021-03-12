package com.nsu.ccfit.weatherapplication.data

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Assert.assertEquals
import org.junit.Test


class CityRepositoryImplTest {

    private val cityDataSource: CityDataSource = mockk()
    private val maybeCity: Maybe<City> = mockk()
    private val repository = CityRepositoryImpl(cityDataSource)
    private val completable: Completable = mockk()

    @Test
    fun `get cities EXPECT list of city`() {
        every { cityDataSource.getCities() } returns listOf(String())

        val cities = repository.getCities()

        assertEquals(listOf(String()), cities)
    }

    @Test
    fun `get city by name EXPECT maybe city`() {
        every { cityDataSource.getCity(String()) } returns maybeCity

        val gottenMaybeCity: Maybe<City> = repository.getCity(String())

        assertEquals(maybeCity, gottenMaybeCity)
    }

    @Test
    fun `create city by name EXPECT maybe city`() {
        every { cityDataSource.createCity(String()) } returns maybeCity

        val gottenMaybeCity: Maybe<City> = repository.createCity(String())

        assertEquals(maybeCity, gottenMaybeCity)
    }

    @Test
    fun `delete city by name EXPECT completable`() {
        every { cityDataSource.deleteCity(String()) } returns completable

        val gottenAnswer: Completable = repository.deleteCity(String())

        assertEquals(completable, gottenAnswer)
    }
}
