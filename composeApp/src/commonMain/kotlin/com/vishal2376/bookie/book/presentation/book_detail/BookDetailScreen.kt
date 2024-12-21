package com.vishal2376.bookie.book.presentation.book_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vishal2376.bookie.book.domain.Book

@Composable
fun BookDetailScreenRoot(book: Book?) {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Text("Book detail of $book")
	}

}