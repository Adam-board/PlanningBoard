package uk.ac.abertay.planningboard

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {


    abstract fun getNoteDao() : NoteDao


    companion object{

        const val DATABASE_NAME = "note_database"

        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context: Context)  : NoteDatabase{

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance

            }
        }
    }

}