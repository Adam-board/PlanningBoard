package uk.ac.abertay.planningboard

import androidx.lifecycle.LiveData

class TodoRepo(private val todoDao: TodoDao) {


    val allTodo: LiveData<List<Todo>> = todoDao.getAllTodo()

    suspend fun insert(todo: Todo){

        todoDao.insert(todo)

    }


    suspend fun delete(todo: Todo){


        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo){

        todoDao.update(todo.id, todo.title, todo.todo)
    }

}