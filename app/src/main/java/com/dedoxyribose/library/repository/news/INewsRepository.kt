package com.dedoxyribose.library.repository.news

import com.dedoxyribose.library.model.News

interface INewsRepository {
    suspend fun getNews(): List<News>
}
