package com.vishal2376.bookie.book.presentation.book_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.book_cover_not_found
import coil3.compose.rememberAsyncImagePainter
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.components.HeadingTextUI
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookDetailUI(book: Book) {
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

	// Title & Author
	Column(
		modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			book.title,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			style = MaterialTheme.typography.titleLarge,
			color = MaterialTheme.colorScheme.primary,
		)
		Text(
			book.authors.first(),
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.onPrimaryContainer,
		)
	}

	Spacer(modifier = Modifier.height(16.dp))

	// Cover & Additional Info
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
	) {
		Box(
			modifier = Modifier
				.width(160.dp)
				.clip(RoundedCornerShape(16.dp))
				.height(230.dp)
		) {
			when (val result = imageLoadResult) {
				null -> {
					Box(
						modifier = Modifier.fillMaxSize(),
						contentAlignment = Alignment.Center
					) { CircularProgressIndicator() }
				}

				else -> {
					Image(
						modifier = Modifier.fillMaxSize(),
						painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_cover_not_found),
						contentDescription = null,
						contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit
					)
				}
			}
		}

		Column(
			verticalArrangement = Arrangement.spacedBy(12.dp),
		) {
			ChipInfoUI(icon = Icons.Outlined.Star, text = "${book.averageRating} rating")
			ChipInfoUI(
				icon = Icons.AutoMirrored.Outlined.LibraryBooks,
				text = "${book.numPages} pages"
			)
			ChipInfoUI(icon = Icons.Outlined.BookOnline, text = "${book.firstPublisher}")
			ChipInfoUI(
				icon = Icons.Outlined.Translate,
				text = book.language?.joinToString(", ") ?: ""
			)
		}
	}

	// Description
	HeadingTextUI("Description")
	Text(
		modifier = Modifier.fillMaxWidth().padding(24.dp, 8.dp),
		text = book.description ?: "",
		style = MaterialTheme.typography.bodySmall
	)
}