package com.dedoxyribose.library.domain.newsdetails

import com.dedoxyribose.library.model.News

interface INewsDetailsInteractor {
    suspend fun getNews(id: Long): News
}
