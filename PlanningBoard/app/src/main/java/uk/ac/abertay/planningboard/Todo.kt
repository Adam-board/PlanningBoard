package uk.ac.abertay.planningboard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)val id : Int?,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "todo")val todo: String?,
): java.io.Serializable
