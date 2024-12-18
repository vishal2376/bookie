package com.vishal2376.bookie.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailScreenRoot
import com.vishal2376.bookie.book.presentation.book_list.BookListScreenRoot
import com.vishal2376.bookie.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation() {
	val navController = rememberNavController()
	val bookListViewModel = koinViewModel<BookListViewModel>()

	NavHost(
		navController = navController,
		startDestination = Route.BookGraph
	) {
		navigation<Route.BookGraph>(startDestination = Route.BookList) {
			composable<Route.BookList> {
				BookListScreenRoot(viewModel = bookListViewModel,
					onBookClick = { book ->
						navController.navigate(Route.BookDetail(book.id))
					})
			}

			composable<Route.BookDetail> { entry ->
				val args = entry.toRoute<Route.BookDetail>()
				BookDetailScreenRoot(args.id)
			}
		}
	}
}