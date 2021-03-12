package com.nsu.ccfit.weatherapplication.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nsu.ccfit.weatherapplication.domain.GetCitiesUseCase
import com.nsu.ccfit.weatherapplication.domain.RemoveCityUseCase
import com.nsu.ccfit.weatherapplication.presentation.list.ListViewModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ListViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getCitiesUseCase: GetCitiesUseCase = mockk()
    private val removeCityUseCase: RemoveCityUseCase = mockk()
    private val completable: Completable = mockk()

    private val listViewModel = ListViewModel(getCitiesUseCase, removeCityUseCase)

    @Test
    fun `load cities EXPECT set list of city in cities list`() {
        every { getCitiesUseCase() } returns listOf(String())

        listViewModel.loadCities()

        assertEquals(listOf(String()), listViewModel.cityList.value)
    }

    @Test
    fun `remove city EXPECT remove city from cities list`() {
        every { removeCityUseCase(String()) } returns completable

        listViewModel.removeCity(String())

        assertEquals(listOf(String()), listViewModel.cityList.value)
    }
}