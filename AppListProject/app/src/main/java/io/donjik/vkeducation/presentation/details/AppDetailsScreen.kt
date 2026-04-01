package io.donjik.vkeducation.presentation.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.donjik.vkeducation.R
import androidx.compose.foundation.layout.Box
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.presentation.ui.theme.VkEducationTheme
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue

@Composable
fun AppDetailsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.isError || state.app == null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Ошибка загрузки")
            }
        }

        else -> {
            val app = state.app ?: return

            AppDetailsScreen(
                app = app,
                isInWishlist = state.isInWishlist,
                onWishlistClick = viewModel::toggleWishlist,
                onBackClick = onBackClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AppDetailsScreen(
    app: App,
    isInWishlist: Boolean,
    onWishlistClick: () -> Unit,
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
            isInWishlist = isInWishlist,
            onWishlistClick = onWishlistClick,
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
            id = "1",
            name = "Тестовое Приложение",
            developer = "VK Education",
            category = "Тест",
            ageRating = 12,
            size = 150f,
            iconUrl = "",
            screenshotUrlList = emptyList(),
            description = "Это описание для превью Compose.",
            isInWishlist = false
        )

        AppDetailsScreen(
            app = mockApp,
            isInWishlist = false,
            onWishlistClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
