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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class loginPage: Fragment() {


    private val RC_SIGN_IN = 123

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()



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

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.loginWithGoogle.setOnClickListener {

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

        binding.LoginButton.setOnClickListener {view: View ->
            val emailAddressSignup = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (emailAddressSignup.isNotEmpty() && pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(emailAddressSignup, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            view.findNavController().navigate(R.id.action_loginPage_to_landingPage)
                        }
                    }
            }else{
                Toast.makeText(requireActivity(), "Empty fields are NOT allowed!" , Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun checkLoggedIn(){
        if(firebaseAuth.currentUser != null) {

            view?.findNavController()?.navigate(R.id.action_loginPage_to_landingPage)

        }
    }

    override fun onStart() {
        super.onStart()
        checkLoggedIn()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Handle successful sign-in
            } catch (e: ApiException) {
                // Handle sign-in failure
            }
        }
    }
}