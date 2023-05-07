package com.example.bbcnewsapp.features.new_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.bbcnewsapp.core.data.Resource
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.core.domain.usecases.remote.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetNewsListViewModel @Inject constructor(
    private val useCase: GetNewsUseCase
) :
    ViewModel() {

    private val _newsResponse = MutableLiveData<Resource<List<Article>>>()
    val newsResponse: LiveData<Resource<List<Article>>>
        get() = _newsResponse


    init {

        viewModelScope.launch {
            useCase.invoke()
                .collect { response ->
                    _newsResponse.value = response
                }
        }
    }


}