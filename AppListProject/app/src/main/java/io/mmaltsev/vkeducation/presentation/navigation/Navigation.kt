package io.mmaltsev.vkeducation.presentation.navigation

// Константы для навигации
const val APP_LIST_SCREEN = "app_list"
const val APP_DETAILS_SCREEN = "app_details"
const val APP_NAME_PARAM = "appName"

const val APP_LIST_ROUTE = APP_LIST_SCREEN
const val APP_DETAILS_ROUTE = "$APP_DETAILS_SCREEN/{$APP_NAME_PARAM}"

fun appDetailsRoute(appName: String): String {
    return "$APP_DETAILS_SCREEN/$appName"
}