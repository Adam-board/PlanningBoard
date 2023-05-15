package uk.ac.abertay.planningboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import timber.log.Timber
import uk.ac.abertay.planningboard.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {

            old_note = intent.getSerializableExtra("current_note") as Note
            binding.editNoteTitle.setText(old_note.title)
            binding.EditNote.setText(old_note.note)
            isUpdate = true

        }catch (e: Exception){

            e.printStackTrace()

        }

        binding.confirmImage.setOnClickListener {

            val title = binding.editNoteTitle.text.toString()
            val note_desc = binding.EditNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){

                val formatter = SimpleDateFormat ( "EEE, d MMM yyyy HH:mm a")

                if(isUpdate) {

                    note = Note(
                        old_note.id,title,note_desc,formatter.format(Date()))

                }else{

                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{

                Toast.makeText(this@AddNoteActivity, "Please enter at least a title or a note body", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        binding.backArrow.setOnClickListener{

            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i( "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i( "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i( "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i( "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i( "onRestart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i( "onStop called")
    }


}