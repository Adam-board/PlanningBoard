package uk.ac.abertay.planningboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding

class LandingPage : Fragment() {

    private lateinit var viewModel: LandingViewModel
    private lateinit var binding: LandingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.landing_fragment, container, false
    )

        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
        binding.landingViewModel = viewModel
        binding.setLifecycleOwner (this)


        binding.gotoGoals.setOnClickListener{view :View->
        view.findNavController().navigate(R.id.action_landingPage_to_goalsFragment)
        }
        binding.gotoTodo.setOnClickListener{view:View ->
        view.findNavController().navigate(R.id.action_landingPage_to_todoFragment)
        }




        return binding.root
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
        }
}