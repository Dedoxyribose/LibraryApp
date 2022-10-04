package com.dedoxyribose.library.repository.news

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.News

interface INewsRepository {
    suspend fun getNews(offset: Int): List<News>
    fun createNewsDataSource(): PagingSource<Int, News>
}
