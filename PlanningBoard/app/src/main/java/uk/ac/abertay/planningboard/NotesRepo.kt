package uk.ac.abertay.planningboard

import androidx.lifecycle.LiveData

class NotesRepo(private val noteDao: NoteDao) {


    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){

        noteDao.insert(note)

    }


    suspend fun delete(note: Note){


        noteDao.delete(note)
    }

    suspend fun update(note:Note){

        noteDao.update(note)
    }

}