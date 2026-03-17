package io.mmaltsev.vkeducation

// Единый источник данных для всего приложения
object AppDataProvider {

    fun getAppsList(): List<App> = listOf(
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

    fun getDefaultApp(): App = getAppsList().last()
}