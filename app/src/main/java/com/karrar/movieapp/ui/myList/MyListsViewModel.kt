package com.karrar.movieapp.ui.myList

import androidx.lifecycle.*
import com.karrar.movieapp.data.repository.*
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.saveMovie.SaveListInteractionListener
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel(), CreatedListInteractionListener, SaveListInteractionListener {


    private val _createdList = MutableLiveData<UIState<MutableList<CreatedList>?>>()
    val createdList = _createdList.toLiveData()

    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked.toLiveData()

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent.toLiveData()

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>>
        get() = _item

    init {
        getData()
    }

    override fun getData() {
        _createdList.postValue(UIState.Loading)
        wrapWithState({
            accountRepository.getSessionId().collect { sessionId ->
                val response = movieRepository.getAllLists(0, sessionId.toString()).toMutableList()
                _createdList.value = UIState.Success(response)
                checkIfLogIn(sessionId)
            }
        }, {
            _createdList.value = UIState.Error(it.message.toString())
            checkTheError()
        })
    }

    private fun checkTheError() {
        if (_createdList.value  == UIState.Error("response is not successful"))
            _createdList.postValue(UIState.NoLogin)
    }

    private fun checkIfLogIn(sessionId: String?) {
        if (sessionId == "")
            _createdList.postValue(UIState.NoLogin)
    }


    fun onCreateList() {
        _isCreateButtonClicked.postEvent(true)
    }

    fun onClickAddList() {
        wrapWithState({
            accountRepository.getSessionId().collect {
                val item = movieRepository.createList(it.toString(), listName.value.toString())
                if (item.success == true)
                    addList(CreatedList(item.listId ?: 0, 0, listName.value.toString()))
                listName.postValue(null)
            }
        })
        _onCLickAddEvent.postEvent(true)
    }

    private fun addList(createdLists: CreatedList) {
        val oldList = _createdList.value?.toData()?.toMutableList()
        oldList?.add(0, createdLists)
        _createdList.postValue(UIState.Success(oldList))
    }

    override fun onListClick(item: CreatedList) {
        _item.postValue(Event(item))
    }



    override fun onClickSaveList(list: CreatedList) {
        chosenList.postValue(list)
        _newAdd.postValue(true)
    }

    private val _newAdd = MutableLiveData(false)
    var newAdd: LiveData<Boolean> = _newAdd

    private val chosenList = MutableLiveData<CreatedList>()

    private val _message = MutableLiveData<String>()
    var message: LiveData<String> = _message


    fun checkMovie(movieId: Int) {
        wrapWithState({ val result = movieRepository.getListDetails(chosenList.value?.id ?:0)
            if (result.checkIfExist(movieId)) {
                _message.postValue("Fail: this movie is already on the list")
                _newAdd.postValue(false)
            }
            if (!result.checkIfExist(movieId)){
                addMovieToList(movieId)
            }
        })

    }

    private fun addMovieToList(movieId: Int) {
        wrapWithState({ accountRepository.getSessionId().collect {
            movieRepository.addMovieToList(
                it.toString(),
                chosenList.value?.id ?: 0,
                movieId)
            _message.postValue("Susses: The movie has been added")
            getData()
            _newAdd.postValue(false)
        }},{
            _message.postValue("error: No Internet Connection")
            _newAdd.postValue(false)
        })
    }

}