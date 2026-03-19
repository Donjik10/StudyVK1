package io.mmaltsev.vkeducation.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.domain.Category

import io.mmaltsev.vkeducation.data.AppDataProvider

@Composable
fun AppListScreen(
    onAppClick: (App) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel = viewModel()
) {
    val apps by viewModel.apps.collectAsStateWithLifecycle()
    val loadTime by viewModel.loadTime.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.snackbarFlow.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Header(
                onIconClick = { viewModel.onAppIconClick("RuStore") },
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = loadTime,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyColumn {
                items(apps) { app ->
                    AppListItem(
                        app = app,
                        onItemClick = { onAppClick(app) },
                        onIconClick = { viewModel.onAppIconClick(app.name) }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}
@Composable
fun Header(
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://brands-prod.cdn-tinkoff.ru/general_logo/rustore.png",
                contentDescription = "RuStore Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onIconClick() }
            )

            Text(
                text = "RuStore",
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row {
            IconButton(onClick = { /* Поиск */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Поиск"
                )
            }

            IconButton(onClick = { /* Меню */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Меню"
                )
            }
        }
    }
}
