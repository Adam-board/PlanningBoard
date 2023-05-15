package uk.ac.abertay.planningboard

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import uk.ac.abertay.planningboard.databinding.LoginFragmentBinding

class loginPage: Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        //sets up instance of Firebase Authentication
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.GoogleLogin.setOnClickListener {
            signInGoogle()
        }


        //This button will take a user to the create an account page
        binding.SignupButton.setOnClickListener { view: View ->
            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }

        //This button is used for checking the details that were input
        binding.LoginButton.setOnClickListener { view: View ->
            val emailAddressSignup = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (emailAddressSignup.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(emailAddressSignup, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            view.findNavController().navigate(R.id.action_loginPage_to_landingPage)
                        }
                    }
            }else{
                Toast.makeText(requireActivity(), "Empty fields are NOT allowed!", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }


    private fun checkLoggedIn() {
        if (auth.currentUser != null) {
            view?.findNavController()?.navigate(R.id.action_loginPage_to_landingPage)
        }
    }

    override fun onStart() {
        super.onStart()
        checkLoggedIn()
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
                    if(result.resultCode == Activity.RESULT_OK){

                        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                        handlerResults(task)
                    }




    }

    private fun handlerResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                UpdateUI(account)

            }

        }else{
            Toast.makeText(requireActivity(), task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
        }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                view?.findNavController()?.navigate(R.id.action_loginPage_to_landingPage)

            }else{
                Toast.makeText(requireActivity(), it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }
}