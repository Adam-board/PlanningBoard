package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import uk.ac.abertay.planningboard.databinding.CalendarFragmentBinding

class CalendarFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: CalendarFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.calendar_fragment, container, false
    )
        return binding.root
    }



}