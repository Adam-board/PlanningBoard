package uk.ac.abertay.planningboard

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uk.ac.abertay.planningboard.databinding.TodoFragmentBinding

class TodoFragment: Fragment(), TodoAdapter.TodoClickListener, PopupMenu.OnMenuItemClickListener
{

    private lateinit var binding : TodoFragmentBinding
    private lateinit var database: TodoDatabase
    lateinit var viewModel: TodoViewModel
    lateinit var adapter: TodoAdapter
    lateinit var selectedTodo : Todo

    private val updateTodo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == Activity.RESULT_OK){

            val todo = result.data?.getSerializableExtra("todo") as? Todo
            if(todo != null){

                viewModel.updateTodo(todo)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.todo_fragment, container, false
    )

        initUI()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(TodoViewModel::class.java)

        viewModel.alltodo.observe(viewLifecycleOwner) { list ->

            list?.let {

                adapter.updateList(list)
            }
        }


        database = TodoDatabase.getDatabase(requireContext())

        return binding.root
    }


    private fun initUI() {

        binding.TodoView.setHasFixedSize(true)
        binding.TodoView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        adapter = TodoAdapter(requireContext(), this)
        binding.TodoView.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

            if (result.resultCode == Activity.RESULT_OK){

                val todo = result.data?.getSerializableExtra("todo") as? Todo
                if (todo != null){

                    viewModel.insertTodo(todo)
                }
            }
        }

        binding.fbAddTodo.setOnClickListener{

            val intent = Intent(requireContext(), AddTodoActivity::class.java)
            getContent.launch(intent)

        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != null){

                    adapter.filterList(newText)
                }
                return true
            }
        })

    }

    override fun onItemClicked(todo:Todo) {

        val intent = Intent(requireContext(), AddTodoActivity::class.java)
        intent.putExtra("current_todo",todo)
        updateTodo.launch(intent)

    }

    override fun onLongItemClicked(todo:Todo, cardView: CardView) {
        selectedTodo = todo
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {

        val popup = PopupMenu(requireContext(), cardView)
        popup.setOnMenuItemClickListener(this@TodoFragment)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.deleteNote){

            viewModel.deleteTodo(selectedTodo)
            return true

        }
        if(item?.itemId == R.id.shareNote){

            onShare(selectedTodo)

        }
        return false
    }

    private fun onShare(todo: Todo) {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.shareFeatureTextTodo, selectedTodo.title, selectedTodo.todo))
            .setType("text/plain")
            .intent

        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(), getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG).show()
        }
    }

}
