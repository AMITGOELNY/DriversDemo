package com.goel.riderapp.ui.route.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goel.riderapp.domain.models.Route
import com.goel.riderapp.domain.usecase.GetRouteInfoUseCase
import com.goel.riderapp.util.UIState
import kotlinx.coroutines.launch

class RouteInfoScreenViewModel(
    private val id: String,
    private val getRouteInfoUseCase: GetRouteInfoUseCase
) : ViewModel() {

    var screenState: UIState<List<RouteUI>> by mutableStateOf(UIState.Loading)
        private set

    init {
        getRouteInfo()
    }

    private fun getRouteInfo() {
        viewModelScope.launch {
            screenState = try {
                val routeInfo = getRouteInfoUseCase.routeInfo(id.toInt())
                if (routeInfo.isNotEmpty()) {
                    UIState.Loaded(routeInfo.toRouteUI())
                } else {
                    UIState.Empty
                }
            } catch (e: Exception) {
                UIState.Error
            }
        }
    }
}

private fun List<Route>.toRouteUI(): List<RouteUI> =
    map { RouteUI(it.id.toString(), it.type, it.name) }

data class RouteUI(val id: String, val type: String, val name: String)
