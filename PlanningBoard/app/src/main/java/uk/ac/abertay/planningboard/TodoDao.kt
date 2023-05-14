package uk.ac.abertay.planningboard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Select * from todo_table order by id ASC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Query("UPDATE todo_table Set title = :title, todo = :note WHERE id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)
}