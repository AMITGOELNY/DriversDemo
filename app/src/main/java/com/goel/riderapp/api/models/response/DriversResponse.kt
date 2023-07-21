package com.goel.riderapp.api.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriversResponse(
    @SerialName("drivers")
    val drivers: List<DriverNetworkModel>,
    @SerialName("routes")
    val routes: List<RouteNetworkModel>
)

@Serializable
data class DriverNetworkModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)

@Serializable
data class RouteNetworkModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)
