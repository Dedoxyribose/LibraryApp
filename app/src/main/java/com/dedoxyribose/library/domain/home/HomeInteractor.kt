package com.dedoxyribose.library.domain.home

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.repository.news.INewsRepository
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val newsRepository: INewsRepository
) : IHomeInteractor {
    override fun createNewsSource(): PagingSource<Int, News> {
        return newsRepository.createNewsDataSource()
    }
}
