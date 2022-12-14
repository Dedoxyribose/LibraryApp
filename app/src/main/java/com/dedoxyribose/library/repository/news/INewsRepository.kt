package com.dedoxyribose.library.repository.news

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.News

interface INewsRepository {
    suspend fun getNews(offset: Int, take: Int): List<News>
    fun createNewsDataSource(): PagingSource<Int, News>
    suspend fun getNews(id: Long): News
}
