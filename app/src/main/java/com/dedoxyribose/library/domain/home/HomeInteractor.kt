package com.dedoxyribose.library.domain.home

import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.repository.news.INewsRepository
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val newsRepository: INewsRepository
) : IHomeInteractor {
    override suspend fun getNews(): List<News> {
        return newsRepository.getNews()
    }
}
