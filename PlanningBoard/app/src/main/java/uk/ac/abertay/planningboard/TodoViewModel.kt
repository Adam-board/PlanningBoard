package uk.ac.abertay.planningboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val repo : TodoRepo

    val alltodo : LiveData<List<Todo>>

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        repo = TodoRepo(dao)
        alltodo = repo.allTodo
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {

        repo.delete(todo)
    }


    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {

        repo.insert(todo)
    }


    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {

        repo.update(todo)
    }

}