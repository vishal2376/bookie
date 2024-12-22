package com.vishal2376.bookie.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailAction
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailScreenRoot
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailViewModel
import com.vishal2376.bookie.book.presentation.book_list.BookListScreenRoot
import com.vishal2376.bookie.book.presentation.book_list.BookListViewModel
import com.vishal2376.bookie.book.presentation.viewmodels.SharedViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation() {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = Route.BookGraph
	) {
		navigation<Route.BookGraph>(startDestination = Route.BookList) {
			composable<Route.BookList> {
				val bookListViewModel = koinViewModel<BookListViewModel>()
				val sharedViewModel = it.sharedKoinViewModel<SharedViewModel>(navController)

				LaunchedEffect(Unit) {
					sharedViewModel.onSelectBook(null)
				}

				BookListScreenRoot(viewModel = bookListViewModel,
					onBookClick = { book ->
						sharedViewModel.onSelectBook(book)
						navController.navigate(Route.BookDetail(book.id))
					})
			}

			composable<Route.BookDetail> {
				val bookDetailViewModel = koinViewModel<BookDetailViewModel>()
				val sharedViewModel = it.sharedKoinViewModel<SharedViewModel>(navController)
				val selectedBook by sharedViewModel.selectedBook.collectAsStateWithLifecycle()

				LaunchedEffect(selectedBook) {
					selectedBook?.let { book ->
						bookDetailViewModel.onAction(BookDetailAction.OnSelectedBookChanged(book))
					}
				}

				BookDetailScreenRoot(viewModel = bookDetailViewModel,
					onClickBack = {
						navController.navigateUp()
					})
			}
		}
	}
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(navController: NavController): T {
	val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
	val parentEntry = remember(this) {
		navController.getBackStackEntry(navGraphRoute)
	}
	return koinViewModel(viewModelStoreOwner = parentEntry)

}