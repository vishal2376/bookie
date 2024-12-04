package com.vishal2376.bookie.book.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.book_cover_error
import coil3.compose.rememberAsyncImagePainter
import com.vishal2376.bookie.book.domain.Book
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookItemUI(
	book: Book,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	val gradientColor = listOf(Color.Transparent, MaterialTheme.colorScheme.primaryContainer)
	Column(
		modifier = modifier
			.wrapContentSize()
			.width(160.dp)
			.clip(RoundedCornerShape(16.dp))
			.clickable {
				onClick()
			}
	) {
		//image
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp)
		) {
			// book cover
			var imageLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }
			val painter = rememberAsyncImagePainter(
				model = book.imageUrl,
				onSuccess = {
					imageLoadResult =
						if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
							Result.success(it.painter)
						} else {
							Result.failure(Exception("Invalid image size"))
						}
				}, onError = {
					it.result.throwable.printStackTrace()
					imageLoadResult = Result.failure(it.result.throwable)
				}
			)

			when (val result = imageLoadResult) {
				null -> CircularProgressIndicator()
				else -> {
					Image(
						painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_cover_error),
						contentDescription = null,
						contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit
					)
				}
			}

			// cover gradient blend
			Box(
				modifier = Modifier
					.height(100.dp)
					.fillMaxWidth()
					.align(Alignment.BottomCenter)
					.background(Brush.verticalGradient(gradientColor))
			)
			// rating
			Row(
				modifier = Modifier
					.padding(8.dp)
					.background(
						MaterialTheme.colorScheme.primary,
						RoundedCornerShape(8.dp)
					)
					.align(Alignment.BottomEnd)
					.padding(4.dp, 2.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(2.dp)
			) {
				Icon(
					modifier = Modifier.size(16.dp),
					imageVector = Icons.Filled.Star,
					tint = MaterialTheme.colorScheme.onPrimary,
					contentDescription = null
				)
				Text(
					text = book.averageRating.toString(),
					style = MaterialTheme.typography.labelMedium,
					color = MaterialTheme.colorScheme.onPrimary,
					fontWeight = FontWeight.Bold
				)
			}
		}
		//info
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.primaryContainer)
				.padding(8.dp)
		) {
			Text(
				text = book.title, style = MaterialTheme.typography.labelMedium,
				maxLines = 1,
				fontWeight = FontWeight.Bold,
				overflow = TextOverflow.Ellipsis
			)
			Text(
				text = book.authors.first(), style = MaterialTheme.typography.labelSmall,
				color = MaterialTheme.colorScheme.onPrimaryContainer
			)
		}
	}
}
