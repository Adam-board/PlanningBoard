package uk.ac.abertay.planningboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import uk.ac.abertay.planningboard.databinding.NoteFragmentBinding


/**
 * A simple NoteFragment class.
 */
class NoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: NoteFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.note_fragment, container, false
    )
        return binding.root
    }

}
