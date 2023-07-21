package com.goel.riderapp.ui.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.goel.riderapp.R
import com.goel.riderapp.ui.route.viewmodel.RouteInfoScreenViewModel
import com.goel.riderapp.ui.route.viewmodel.RouteUI
import com.goel.riderapp.util.UIState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RouteInfoScreen(
    id: String,
    viewModel: RouteInfoScreenViewModel = koinViewModel { parametersOf(id) },
    onBackClick: () -> Unit
) {
    Scaffold(topBar = { RouteTopAppBar(onBackClick) }) { padding ->
        Column(Modifier.padding(padding)) {
            when (val state = viewModel.screenState) {
                UIState.Empty -> RouteInfoEmptyView()
                UIState.Error -> RouteInfoErrorView()
                is UIState.Loaded -> RouteInfoContent(state.data)

                UIState.Loading ->
                    LinearProgressIndicator(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
            }
        }
    }
}

@Composable
private fun RouteInfoEmptyView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.no_routes_available),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RouteInfoErrorView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.error_occurred_fetching_the_route_info),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RouteInfoContent(routes: List<RouteUI>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = routes) { route ->
            RoutesCard(route = route)
        }
    }
}

@Composable
private fun RouteTopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = stringResource(R.string.route_info)) },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    )
}
