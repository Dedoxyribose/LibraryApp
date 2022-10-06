package com.dedoxyribose.library.domain.newsdetails

import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.repository.news.INewsRepository
import javax.inject.Inject

class NewsDetailsInteractor @Inject constructor(
    private val newsRepository: INewsRepository
) : INewsDetailsInteractor {
    override suspend fun getNews(id: Long): News {
        return newsRepository.getNews(id)
    }
}
