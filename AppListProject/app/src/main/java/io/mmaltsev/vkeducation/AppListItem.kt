package io.mmaltsev.vkeducation
import io.mmaltsev.vkeducation.ui.theme.VkEducationTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun AppListItem(
    app: App,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Иконка приложения
        AsyncImage(
            model = app.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Информация о приложении
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = app.developer,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = getCategoryText(app.category),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложение"
    Category.GAME -> "Игра"
}

@Preview
@Composable
private fun PreviewAppListItem() {
    io.mmaltsev.vkeducation.ui.theme.VkEducationTheme {  // Полный путь к теме
        AppListItem(
            app = App(
                name = "СберБанк Онлайн – с Салютом",
                developer = "Больше чем банк",
                category = Category.APP,
                ageRating = 0,
                size = 100f,
                iconUrl = "",
                screenshotUrlList = emptyList(),
                description = ""
            ),
            onClick = {}
        )
    }
}