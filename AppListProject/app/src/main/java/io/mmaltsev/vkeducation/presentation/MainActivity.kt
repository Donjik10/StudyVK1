package io.mmaltsev.vkeducation.presentation

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
import io.mmaltsev.vkeducation.presentation.details.AppDetailsRoute
import io.mmaltsev.vkeducation.presentation.list.AppListScreen
import io.mmaltsev.vkeducation.presentation.navigation.*
import io.mmaltsev.vkeducation.presentation.ui.theme.VkEducationTheme

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

            AppDetailsRoute(
                appName = appName,
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.safeDrawingPadding()
            )
        }
    }
}