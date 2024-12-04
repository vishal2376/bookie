package com.vishal2376.bookie.book.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vishal2376.bookie.book.domain.Book

@Composable
fun BookListUI(
	books: List<Book>,
	onClickBook: (Book) -> Unit,
	scrollState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
	modifier: Modifier = Modifier
) {
	LazyVerticalStaggeredGrid(
		modifier = modifier,
		state = scrollState,
		columns = StaggeredGridCells.FixedSize(160.dp),
		verticalItemSpacing = 24.dp,
		horizontalArrangement = Arrangement.spacedBy(
			24.dp,
			Alignment.CenterHorizontally
		),
		content = {
			items(books, key = { it.id }) { book ->
				BookItemUI(
					book = book,
					onClick = { onClickBook(book) }
				)
			}
		})
}