package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import uk.ac.abertay.planningboard.databinding.TodoFragmentBinding

class TodoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: TodoFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.todo_fragment, container, false
    )
        return binding.root
    }

}