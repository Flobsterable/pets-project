package com.example.pets_project.viewModels

import androidx.lifecycle.ViewModel
import com.example.pets_project.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun isHasAuthToken(): Boolean {
        return repository.isHasAuthToken()
    }
}
