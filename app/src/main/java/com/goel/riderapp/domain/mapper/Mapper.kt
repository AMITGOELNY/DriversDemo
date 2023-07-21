package com.goel.riderapp.domain.mapper

import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.ui.driver.viewModel.DriverUI

fun List<Driver>.mapToDriverUI() = map {
    DriverUI(it.id, it.name)
}
