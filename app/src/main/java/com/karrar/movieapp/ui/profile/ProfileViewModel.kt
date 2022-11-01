package com.karrar.movieapp.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.CheckIfLoggedInUseCase
import com.karrar.movieapp.domain.usecase.GetAccountDetailsUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.myList.myListUIState.MyListUIEvent
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val accountUIStateMapper: AccountUIStateMapper,
    private val checkIfLoggedInUseCase: CheckIfLoggedInUseCase
) : BaseViewModel() {

    private val _profileDetailsUIState = MutableStateFlow(ProfileUIState())
    val profileDetailsUIState = _profileDetailsUIState.asStateFlow()

    val profileUIEvent: MutableStateFlow<Event<ProfileUIEvent>?> = MutableStateFlow(null)

    init {
        getData()
    }

    override fun getData() {
        getProfileDetails()
    }

    private fun getProfileDetails() {
        if (checkIfLoggedInUseCase()){
            _profileDetailsUIState.update {
                it.copy(isLoading = true, isLoggedIn = true, error = false)
            }

            viewModelScope.launch {
                try {
                    val accountDetails = accountUIStateMapper.map(getAccountDetailsUseCase())
                    _profileDetailsUIState.update {
                        it.copy(
                            avatarPath = accountDetails.avatarPath,
                            name = accountDetails.name,
                            username = accountDetails.username,
                            isLoading = false
                        )
                    }
                } catch (t: Throwable) {
                    _profileDetailsUIState.update {
                        it.copy(isLoading = false, error = true)
                    }
                }
            }
        } else {
            _profileDetailsUIState.update {
                it.copy(isLoggedIn = false)
            }
        }
    }

    fun onClickRatedMovies() {
        profileUIEvent.update { Event(ProfileUIEvent.RatedMoviesEvent) }
    }

    fun onClickLogout() {
        profileUIEvent.update { Event(ProfileUIEvent.DialogLogoutEvent) }
    }

    fun onClickWatchHistory() {
        profileUIEvent.update { Event(ProfileUIEvent.WatchHistoryEvent) }
    }

    fun onClickLogin() {
        profileUIEvent.update { Event(ProfileUIEvent.LoginEvent) }
    }
}