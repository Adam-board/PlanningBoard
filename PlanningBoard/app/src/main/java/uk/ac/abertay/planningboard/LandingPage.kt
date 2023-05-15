package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding

class LandingPage : Fragment() {


    private lateinit var viewModel: LandingViewModel
    private lateinit var binding: LandingFragmentBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.landing_fragment, container, false
    )

        firebaseAuth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
        binding.landingViewModel = viewModel
        binding.setLifecycleOwner (this)

        checkLoggedIn()

        binding.gotoNotes.setOnClickListener{view :View->
        view.findNavController().navigate(R.id.action_landingPage_to_noteFragment)
        }
        binding.gotoTodo.setOnClickListener{view:View ->
        view.findNavController().navigate(R.id.action_landingPage_to_todoFragment)
        }

        binding.CalendarButton.setOnClickListener{view:View ->

            view.findNavController().navigate((R.id.action_landingPage_to_calendarFragment))
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkLoggedIn()
    }
    private fun checkLoggedIn(){
        if(firebaseAuth.currentUser == null) {

            view?.findNavController()?.navigate(R.id.action_landingPage_to_loginPage)
        }else{

            binding.UsernameText.text = firebaseAuth.currentUser!!.displayName
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
        }
}