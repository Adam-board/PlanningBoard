package uk.ac.abertay.planningboard

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {


    abstract fun getTodoDao() : TodoDao


    companion object{

        @Volatile
        private var INSTANCE : TodoDatabase? = null

        fun getDatabase(context: Context)  : TodoDatabase{

            return INSTANCE ?: synchronized(this){

                val instance2 = Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                DATABASE_NAME2
                ).build()

                INSTANCE = instance2
                instance2

            }
        }
    }

}