package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.abertay.planningboard.databinding.LogoutFunctionalityBinding

class logoutFunctionality: Fragment(){

    private lateinit var binding: LogoutFunctionalityBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.logout_functionality, container, false
    )

        binding.setLifecycleOwner(this)
        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser != null) {
            firebaseAuth.signOut()
            view?.findNavController()?.navigate(R.id.action_logoutFunctionality_to_loginPage)
        }else{

            view?.findNavController()?.navigate(R.id.myNavHostFragment)
        }



        return binding.root
    }
    private fun checkLoggedIn(){
        if(firebaseAuth.currentUser == null) {

            view?.findNavController()?.navigate(R.id.action_logoutFunctionality_to_loginPage)
        }
    }

    override fun onStart() {
        super.onStart()
        checkLoggedIn()
    }

}