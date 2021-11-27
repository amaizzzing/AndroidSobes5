package com.amaizzzing.sobes5.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.amaizzzing.sobes5.data.repositories.IRedditLocalRepository
import com.amaizzzing.sobes5.data.repositories.IRedditRepository
import com.amaizzzing.sobes5.data.services.network.INetworkStatus
import com.amaizzzing.sobes5.ui.BaseState
import com.amaizzzing.sobes5.ui.models.CommentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val redditRepository: IRedditRepository,
    private val redditLocalRepository: IRedditLocalRepository,
    private val networkStatusService: INetworkStatus
): BaseViewModel<BaseState>() {
    fun getTopComments() = viewModelScope.launch {
        setData(BaseState.Loading)

        if (networkStatusService.isOnline()) {
            redditRepository
                .getTopComments()
                .flowOn(Dispatchers.IO)
                .collectLatest { comments ->
                    withContext(viewModelScope.coroutineContext) {
                        setData(BaseState.Success(comments))
                    }
                }
        } else {
            redditLocalRepository
                .getAllComments()
                .flowOn(Dispatchers.IO)
                .collectLatest { comments ->
                    withContext(viewModelScope.coroutineContext) {
                        setData(BaseState.Success(comments.map { CommentModel(it) }))
                    }
                }
        }
    }
}