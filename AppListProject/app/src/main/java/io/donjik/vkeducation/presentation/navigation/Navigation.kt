package io.donjik.vkeducation.presentation.navigation

// Константы для навигации

const val APP_NAME_PARAM = "appName"
const val APP_LIST_SCREEN = "app_list"
const val APP_DETAILS_SCREEN = "app_details"
const val APP_ID_PARAM = "appId"
const val APP_LIST_ROUTE = APP_LIST_SCREEN
const val APP_DETAILS_ROUTE = "app_details/{$APP_ID_PARAM}"
fun appDetailsRoute(appId: String): String = "app_details/$appId"