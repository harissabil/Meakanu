package com.harissabil.meakanu.ui.agri

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.remote.response.news.ArticlesItem

class AgriViewModel(repository: PlantRepository) : ViewModel() {

    val news: LiveData<PagingData<ArticlesItem>> = repository.getNews().cachedIn(viewModelScope)
}