package io.mmaltsev.vkeducation.presentation.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.domain.Category
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.presentation.ui.theme.VkEducationTheme

@Composable
fun AppDetailsRoute(
    appName: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppDetailsViewModel = viewModel()
) {
    LaunchedEffect(appName) {
        viewModel.loadApp(appName)
    }

    val appState by viewModel.app.collectAsStateWithLifecycle()
    appState?.let { loadedApp ->
        AppDetailsScreen(
            app = loadedApp,
            onBackClick = onBackClick,
            modifier = modifier
        )
    }
}

@Composable
fun AppDetailsScreen(
    app: App,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val underDevelopmentText = stringResource(R.string.under_developement)
    var descriptionCollapsed by remember { mutableStateOf(false) }

    Column(modifier) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            app = app,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
        ScreenshotsList(
            screenshotUrlList = app.screenshotUrlList,
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = app.description,
            collapsed = descriptionCollapsed,
            onReadMoreClick = {
                descriptionCollapsed = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = app.developer,
            onClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {

        val mockApp = App(
            name = "Тестовое Приложение",
            developer = "VK Education",
            category = Category.APP,
            ageRating = 12,
            size = 150f,
            iconUrl = "",
            screenshotUrlList = emptyList(),
            description = "Это описание для превью Compose."
        )

        AppDetailsScreen(
            app = mockApp,
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
