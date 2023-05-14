package uk.ac.abertay.planningboard

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uk.ac.abertay.planningboard.databinding.NoteFragmentBinding


/**
 * A simple NoteFragment class.
 */
class NoteFragment: Fragment(), NotesAdapter.NotesClickListener, PopupMenu.OnMenuItemClickListener
{

    private lateinit var binding :NoteFragmentBinding
    private lateinit var database: NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter
    lateinit var selectedNote : Note

    private val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

    if (result.resultCode == Activity.RESULT_OK){

        val note = result.data?.getSerializableExtra("note") as? Note
        if(note != null){

            viewModel.updateNote(note)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.note_fragment, container, false
    )

        initUI()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(NoteViewModel::class.java)

        viewModel.allnotes.observe(viewLifecycleOwner) { list ->

            list?.let {

                adapter.updateList(list)
            }
        }


        database = NoteDatabase.getDatabase(requireContext())

        return binding.root
    }


    private fun initUI() {

    binding.NotesView.setHasFixedSize(true)
    binding.NotesView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
    adapter = NotesAdapter(requireContext(), this)
    binding.NotesView.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

            if (result.resultCode == Activity.RESULT_OK){

                val note = result.data?.getSerializableExtra("note") as? Note
                if (note != null){

                    viewModel.insertNote(note)
                }
            }
        }

        binding.fbAddNote.setOnClickListener{

            val intent = Intent(context, AddNoteActivity::class.java)
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

    override fun onItemClicked(note: Note) {

        val intent = Intent(requireContext(), AddNoteActivity::class.java)
        intent.putExtra("current_note",note)
        updateNote.launch(intent)

    }

    override fun onLongItemClicked(note: Note, cardView: CardView) {
        selectedNote = note
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {

        val popup = PopupMenu(requireContext(), cardView)
        popup.setOnMenuItemClickListener(this@NoteFragment)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.deleteNote){

            viewModel.deleteNote(selectedNote)
            return true

        }
        if(item?.itemId == R.id.shareNote){

            onShare(selectedNote)

        }
        return false
    }

    private fun onShare(note: Note) {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.shareFeatureText, selectedNote.title, selectedNote.note))
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
