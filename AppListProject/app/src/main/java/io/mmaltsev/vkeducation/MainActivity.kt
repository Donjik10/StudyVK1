package io.mmaltsev.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.mmaltsev.vkeducation.ui.theme.VkEducationTheme
import io.mmaltsev.vkeducation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEducationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = APP_LIST_ROUTE
    ) {
        composable(APP_LIST_ROUTE) {
            AppListScreen(
                onAppClick = { app ->
                    navController.navigate(appDetailsRoute(app.name))
                },
                modifier = Modifier.safeDrawingPadding()
            )
        }

        composable(
            route = APP_DETAILS_ROUTE,
            arguments = listOf(navArgument(APP_NAME_PARAM) { type = NavType.StringType })
        ) { backStackEntry ->
            val appName = backStackEntry.arguments?.getString(APP_NAME_PARAM) ?: ""

            // Используем единый источник данных
            val app = AppDataProvider.getAppsList().find { it.name == appName }
                ?: AppDataProvider.getDefaultApp()

            AppDetailsScreen(
                app = app,
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.safeDrawingPadding()
            )
        }
    }
}