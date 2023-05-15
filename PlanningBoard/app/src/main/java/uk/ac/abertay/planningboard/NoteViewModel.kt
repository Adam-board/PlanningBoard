package uk.ac.abertay.planningboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repo : NotesRepo

    val allnotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repo = NotesRepo(dao)
        allnotes = repo.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repo.delete(note)
    }


    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repo.insert(note)
    }


    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repo.update(note)
    }


}