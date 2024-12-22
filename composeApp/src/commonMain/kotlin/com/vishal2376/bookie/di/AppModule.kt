package com.vishal2376.bookie.di

import com.vishal2376.bookie.book.data.remote.BookDataSourceImpl
import com.vishal2376.bookie.book.data.remote.BookDataStore
import com.vishal2376.bookie.book.data.respository.BookRepository
import com.vishal2376.bookie.book.data.respository.BookRepositoryImpl
import com.vishal2376.bookie.book.presentation.book_detail.BookDetailViewModel
import com.vishal2376.bookie.book.presentation.book_list.BookListViewModel
import com.vishal2376.bookie.book.presentation.viewmodels.SharedViewModel
import com.vishal2376.bookie.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
	single { HttpClientFactory.create(get()) }
	singleOf(::BookDataSourceImpl).bind<BookDataStore>()
	singleOf(::BookRepositoryImpl).bind<BookRepository>()

	viewModelOf(::BookListViewModel)
	viewModelOf(::BookDetailViewModel)
	viewModelOf(::SharedViewModel)
}