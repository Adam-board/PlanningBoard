package uk.ac.abertay.planningboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import timber.log.Timber
import uk.ac.abertay.planningboard.databinding.ActivityAddTodoBinding

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding

    private lateinit var todo: Todo
    private lateinit var old_todo: Todo
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {

            old_todo = intent.getSerializableExtra("current_todo") as Todo
            binding.editTodoTitle.setText(old_todo.title)
            binding.EditTodo.setText(old_todo.todo)
            isUpdate = true

        }catch (e: Exception){

            e.printStackTrace()

        }

        binding.confirmImage.setOnClickListener {

            val title = binding.editTodoTitle.text.toString()
            val note_desc = binding.EditTodo.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){

                if(isUpdate) {

                    todo = Todo(
                        old_todo.id,title,note_desc)

                }else{

                    todo = Todo(
                        null,title,note_desc)
                }

                val intent = Intent()
                intent.putExtra("todo",todo)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{

                Toast.makeText(this@AddTodoActivity, "Please enter at least a title or a note body", Toast.LENGTH_SHORT).show()
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

