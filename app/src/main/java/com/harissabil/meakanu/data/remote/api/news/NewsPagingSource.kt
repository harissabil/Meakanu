package com.harissabil.meakanu.data.remote.api.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.harissabil.meakanu.BuildConfig
import com.harissabil.meakanu.data.remote.response.news.ArticlesItem

class NewsPagingSource(private val apiService: ApiServiceNews) : PagingSource<Int, ArticlesItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getNews(
                apiKey = BuildConfig.API_KEY_NEWS,
                page = position,
                pageSize = params.loadSize
            )
            LoadResult.Page(
                data = responseData.articles,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.articles.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}