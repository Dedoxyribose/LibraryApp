package com.dedoxyribose.library.domain.home

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.News

interface IHomeInteractor {
    fun createNewsSource(): PagingSource<Int, News>
}
