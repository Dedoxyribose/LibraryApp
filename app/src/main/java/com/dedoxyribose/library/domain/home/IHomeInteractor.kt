package com.dedoxyribose.library.domain.home

import com.dedoxyribose.library.model.News

interface IHomeInteractor {
    suspend fun getNews(): List<News>
}
