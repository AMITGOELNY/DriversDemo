package com.goel.riderapp.ui.driver

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.goel.riderapp.R
import com.goel.riderapp.ui.driver.viewModel.DriverUI
import com.goel.riderapp.ui.driver.viewModel.DriversScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriversHomeScreen(
    viewModel: DriversScreenViewModel = koinViewModel(),
    onNavigateToRouteInfo: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DriversTopAppBar(
                onRefreshClicked = viewModel::refresh,
                onSortedClicked = viewModel::updateSorted
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(Modifier.fillMaxSize()) {
                AnimatedVisibility(visible = viewModel.screenState.isLoading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }

                AnimatedVisibility(visible = viewModel.screenState.showErrorHeader) {
                    ErrorRefreshHeader(onRefresh = viewModel::refresh)
                }

                if (viewModel.screenState.showErrorView) {
                    DriversErrorView(viewModel::refresh)
                } else {
                    DriversListView(viewModel.screenState.drivers, onNavigateToRouteInfo)
                }
            }
        }
    }
}

@Composable
private fun DriversErrorView(onRefresh: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.error_occurred_getting_driver_data))
        Button(onClick = onRefresh, shape = MaterialTheme.shapes.medium) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
private fun ErrorRefreshHeader(onRefresh: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.error_occurred_syncing_the_data))
        Button(onClick = onRefresh, shape = MaterialTheme.shapes.medium) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
private fun DriversListView(drivers: List<DriverUI>, onNavigateToRouteInfo: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(drivers) { driver ->
            DriversCard(driver, onNavigateToRouteInfo)
        }
    }
}

@Composable
private fun DriversTopAppBar(
    onRefreshClicked: () -> Unit,
    onSortedClicked: () -> Unit,
) {
    TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 4.dp) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.driver_info),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                IconButton(onClick = onRefreshClicked) {
                    Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
                }
                IconButton(onClick = onSortedClicked) {
                    Icon(imageVector = Icons.Rounded.Sort, contentDescription = null)
                }
            }
        }
    }
}
