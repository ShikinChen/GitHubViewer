package me.shiki.githubviewer.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _showSnackBar = MutableStateFlow(false)

    val isShowSnackBar: StateFlow<Boolean> get() = _showSnackBar.asStateFlow()

    fun toggleSnackBar() {
        _showSnackBar.tryEmit(true)
        viewModelScope.launch {
            delay(1500)
            _showSnackBar.tryEmit(false)
        }
    }
}