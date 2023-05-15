package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.abertay.planningboard.databinding.CalendarFragmentBinding

class CalendarFragment: Fragment() {


    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: CalendarFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.calendar_fragment, container, false
    )

        firebaseAuth = FirebaseAuth.getInstance()
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        checkLoggedIn()
    }

    private fun checkLoggedIn(){
        if(firebaseAuth.currentUser == null) {

            view?.findNavController()?.navigate(R.id.action_calendarFragment_to_loginPage)
        }
    }
}