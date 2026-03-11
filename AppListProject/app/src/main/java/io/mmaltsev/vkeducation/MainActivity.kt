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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.mmaltsev.vkeducation.ui.theme.VkEducationTheme

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
        startDestination = "app_list"
    ) {
        // Экран со списком приложений
        composable("app_list") {
            AppListScreen(
                onAppClick = { app ->
                    // Передаём данные через строку (можно использовать JSON)
                    navController.navigate("app_details/${app.name}")
                },
                modifier = Modifier.safeDrawingPadding()
            )
        }

        // Экран с деталями приложения
        composable(
            route = "app_details/{appName}",
            arguments = listOf(navArgument("appName") { type = NavType.StringType })
        ) { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName") ?: ""

            // Находим приложение по имени (в реальном проекте передавали бы ID)
            val app = getAppsList().find { it.name == appName } ?: getDefaultApp()

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

private fun getAppsList(): List<App> = listOf(
    App(
        name = "СберБанк Онлайн – с Салютом",
        developer = "Больше чем банк",
        category = Category.APP,
        ageRating = 0,
        size = 100f,
        iconUrl = "https://cdn.tvspb.ru/storage/wp-content/uploads/2022/06/sber-vk-3mdthumbnail_gyFF4eN.jpg__0_0x0.jpg",
        screenshotUrlList = emptyList(),
        description = "Больше чем банк - это онлайн-банкинг с расширенными возможностями"
    ),
    App(
        name = "Яндекс.Браузер — с Алисой",
        developer = "Быстрый и безопасный браузер",
        category = Category.APP,
        ageRating = 0,
        size = 80f,
        iconUrl = "https://play-lh.googleusercontent.com/Zg2EKRmLJZHFx3QLTLPAr6lIv8ES8dkxkLKnxKFBHDB1KiRU3H5lK6tod2u9NWh8WgUhOIA9TXyNrjM8rVN9=w600-h300-pc0xffffff-pd",
        screenshotUrlList = emptyList(),
        description = "Быстрый и безопасный браузер с голосовым помощником Алиса"
    ),
    App(
        name = "Почта Mail.ru",
        developer = "Почтовый клиент для любых ящиков",
        category = Category.APP,
        ageRating = 0,
        size = 120f,
        iconUrl = "https://www.alladvertising.ru/porridge/154/180/h_424e67926dbad67291455504f1ddc29c",
        screenshotUrlList = emptyList(),
        description = "Почтовый клиент для любых ящиков"
    ),
    App(
        name = "Яндекс Навигатор",
        developer = "Парковки и заправки – по пути",
        category = Category.APP,
        ageRating = 0,
        size = 150f,
        iconUrl = "https://is1-ssl.mzstatic.com/image/thumb/Purple221/v4/58/8b/86/588b865f-328d-38e2-0d98-571eb8c91196/AppIcon-0-0-1x_U007epad-0-1-85-220.png/1200x630wa.png",
        screenshotUrlList = emptyList(),
        description = "Парковки и заправки – по пути"
    ),
    App(
        name = "Мой МТС",
        developer = "Мой МТС — центр экосистемы МТС",
        category = Category.APP,
        ageRating = 0,
        size = 90f,
        iconUrl = "https://is4-ssl.mzstatic.com/image/thumb/Purple114/v4/02/1d/2d/021d2d46-f946-642a-c21e-7b2f1aec9732/AppIcon-0-1x_U007emarketing-0-0-GLES2_U002c0-512MB-sRGB-0-0-0-85-220-0-0-0-8.png/1200x630wa.png",
        screenshotUrlList = emptyList(),
        description = "Мой МТС — центр экосистемы МТС"
    ),
    App(
        name = "Яндекс — с Алисой",
        developer = "Яндекс — поиск всегда под рукой",
        category = Category.APP,
        ageRating = 0,
        size = 70f,
        iconUrl = "https://i.ytimg.com/vi/pCABnlqZr-w/maxresdefault.jpg",
        screenshotUrlList = emptyList(),
        description = "Яндекс — поиск всегда под рукой"
    ),
    App(
        name = "Гильдия Героев: Экшен ММО РПГ",
        developer = "VK Play",
        category = Category.GAME,
        ageRating = 12,
        size = 223.7f,
        iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        screenshotUrlList = listOf(
        "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
        "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp"
    ),
    description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и сразите мастера подземелья!"
    )
)
private fun getDefaultApp(): App = App(
    name = "Гильдия Героев: Экшен ММО РПГ",
    developer = "VK Play",
    category = Category.GAME,
    ageRating = 12,
    size = 223.7f,
    screenshotUrlList = listOf(
        "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp"
    ),
    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
    description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!"
)