package com.movify.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.movify.repositorios.PeliculaRepositorio
import info.movito.themoviedbapi.model.MovieDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: PeliculaRepositorio) : ViewModel() {

    var foundMovies: List<MovieDb> by mutableStateOf(listOf())
        private set

    fun getMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val foundList = repository.getSearch(query)
            foundMovies = foundList.filter { !it.posterPath.isNullOrEmpty() }
        }
    }

    fun cleanSearch() {
        foundMovies = listOf()
    }
}

class SearchViewModelFactory(private val repository: PeliculaRepositorio):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }

}