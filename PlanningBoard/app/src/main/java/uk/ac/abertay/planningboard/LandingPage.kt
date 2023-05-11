package uk.ac.abertay.planningboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding

class LandingPage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: LandingFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.landing_fragment, container, false
    )


        binding.gotoGoals.setOnClickListener{view :View->
        view.findNavController().navigate(R.id.action_landingPage_to_goalsFragment)
        }
        binding.gotoTodo.setOnClickListener{view:View ->
        view.findNavController().navigate(R.id.action_landingPage_to_todoFragment)
        }


        return binding.root
    }


}