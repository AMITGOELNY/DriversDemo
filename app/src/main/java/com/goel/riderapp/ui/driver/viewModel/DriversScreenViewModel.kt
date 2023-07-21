package com.goel.riderapp.ui.driver.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goel.riderapp.domain.mapper.mapToDriverUI
import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.domain.usecase.DriverInfoUseCase
import com.goel.riderapp.util.NetworkResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class DriversScreenViewModel(
    private val driverInfoUseCase: DriverInfoUseCase
) : ViewModel() {

    var screenState: DriverScreenState by mutableStateOf(DriverScreenState())
        private set

    private val triggerDriversApiFetch: MutableSharedFlow<Unit> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val sorted = MutableStateFlow(false)

    private val apiResponse: Flow<NetworkResult<Unit, Exception>> =
        triggerDriversApiFetch.flatMapConcat { driverInfoUseCase.getDriverRouteInfo() }

    init {
        // triggers initial emission to fetch data from API
        triggerDriversApiFetch.tryEmit(Unit)

        viewModelScope.launch {
            combine(
                apiResponse,
                driverInfoUseCase.getDriverInfoFlow(),
                sorted
            ) { apiResponse, drivers, sorted ->
                val updatedDrivers = if (sorted) {
                    drivers.sortedBy { it.name.split(" ").last() }
                } else {
                    drivers
                }
                updatedDrivers.toUiState(apiResponse)
            }
                .collect { screenState = it }
        }
    }

    private fun List<Driver>.toUiState(apiResponse: NetworkResult<Unit, Exception>) =
        DriverScreenState(
            isLoading = apiResponse is NetworkResult.Loading,
            hasError = apiResponse is NetworkResult.Error,
            drivers = mapToDriverUI()
        )

    /**
     * Used to trigger an emission to fetch an API call for drivers and route info
     */
    fun refresh() {
        triggerDriversApiFetch.tryEmit(Unit)
    }

    fun updateSorted() {
        sorted.update { !sorted.value }
    }

    data class DriverScreenState(
        val drivers: List<DriverUI> = emptyList(),
        val isLoading: Boolean = true,
        val hasError: Boolean = false
    ) {
        val showErrorHeader: Boolean
            get() = hasError && drivers.isNotEmpty()

        val showErrorView: Boolean
            get() = hasError && drivers.isEmpty()
    }
}


data class DriverUI(val id: String, val name: String)

