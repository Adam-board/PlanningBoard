package uk.ac.abertay.planningboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding
import uk.ac.abertay.planningboard.databinding.LoginFragmentBinding

class loginPage: Fragment() {


    private lateinit var binding: LoginFragmentBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { binding = DataBindingUtil.inflate(
        inflater, R.layout.login_fragment, container, false
    )

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SignupButton.setOnClickListener { view: View->

            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.LoginButton.setOnClickListener {view: View ->
            val emailAddressSignup = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (emailAddressSignup.isNotEmpty() && pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(emailAddressSignup, pass).addOnCompleteListener{
                        if (it.isSuccessful){

                            view.findNavController().navigate(R.id.action_loginPage_to_landingPage)
                        }else{

                        }
                    }


            }else{
                Toast.makeText(requireActivity(), "Empty fields are NOT allowed!" , Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }




}