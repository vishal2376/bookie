package com.vishal2376.bookie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.book_cover_not_found
import coil3.compose.rememberAsyncImagePainter
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailAction
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailState
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI
import com.vishal2376.bookie.core.presentation.theme.BookieTheme
import org.jetbrains.compose.resources.painterResource


@Composable
fun TempUI() {
	val book = Book(
		id = "1",
		title = "Harry Potter",
		imageUrl = "",
		authors = listOf("J.K. Rowling"),
		description = "This is a book about Harry Potter",
		language = listOf("eng"),
		firstPublisher = "",
		averageRating = 4.2,
		ratingCount = 120,
		numPages = 122,
		numEditions = 4
	)
	BookDetailScreen(BookDetailState(book = book), {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(state: BookDetailState, onAction: (BookDetailAction) -> Unit) {
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.background,
					titleContentColor = MaterialTheme.colorScheme.primary,
					actionIconContentColor = MaterialTheme.colorScheme.primary
				),
				title = {
					Text(
						text = "Back",
						style = MaterialTheme.typography.titleLarge,
					)
				},
				navigationIcon = {
					IconButton(onClick = {
						onAction(BookDetailAction.OnClickBack)
					}) {
						Icon(
							imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
							tint = MaterialTheme.colorScheme.primary,
							contentDescription = null
						)
					}
				},
				actions = {
					IconButton(onClick = {
						onAction(BookDetailAction.OnClickFavorite)
					}) {
						Icon(
							imageVector = if (state.isFavorite) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
							contentDescription = null
						)
					}
				})
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
				.padding(horizontal = 24.dp),
		) {
			if (state.book == null) {
				EmptyResultUI()
			} else {
				BookDetailUI(state.book)
			}
		}
	}
}

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

	Spacer(modifier = Modifier.height(8.dp))

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
				text = book.language?.joinToString(",") ?: ""
			)
		}
	}

	// Description
	Text(
		text = book.description.toString(),
		style = MaterialTheme.typography.bodySmall
	)
}

@Composable
fun ChipInfoUI(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Icon(
			modifier = Modifier
				.background(
					MaterialTheme.colorScheme.primaryContainer,
					RoundedCornerShape(8.dp)
				)
				.padding(4.dp),
			imageVector = icon,
			tint = MaterialTheme.colorScheme.primary,
			contentDescription = null
		)
		Text(
			text = text,
			style = MaterialTheme.typography.bodyMedium,
		)
	}
}

@Preview
@Composable
private fun TempUIPreview() {
	Column(
		modifier = Modifier.background(Color.White)
	) {
		BookieTheme(darkTheme = true, dynamicColor = false) {
			TempUI()
		}
	}
}