package uk.ac.abertay.planningboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding

class LandingPage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: LandingFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.landing_fragment, container, false
    )

        return binding.root
    }


}