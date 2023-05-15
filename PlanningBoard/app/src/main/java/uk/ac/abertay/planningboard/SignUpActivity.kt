package uk.ac.abertay.planningboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import timber.log.Timber
import uk.ac.abertay.planningboard.databinding.SignupActivityBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignupActivityBinding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.setLifecycleOwner (this)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.AlreadyAccount.setOnClickListener{view: View ->

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }


        checkLoggedIn()



        binding.CreateAccount.setOnClickListener {
            val DisplayName = binding.displayName.text.toString().trim()
            val emailAddressSignup = binding.editTextEmailAddressSignUp.text.toString().trim()
            val pass = binding.editTextPasswordSignUp.text.toString().trim()
            val confirmPass = binding.editTextRetypePassword.text.toString().trim()

            if (emailAddressSignup.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if (pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(emailAddressSignup, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            firebaseAuth.currentUser!!.updateProfile(updateUsersName(DisplayName, firebaseAuth))
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Log.w("SignUpActivity", "SignupFailing")
                            Toast.makeText(this, "Currently Busy or Account may already exist", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "password's do not match", Toast.LENGTH_SHORT).show()
            }


            }else{
                Toast.makeText(this, "Empty fields are NOT allowed!" , Toast.LENGTH_SHORT).show()
        }

        }
    }

private fun checkLoggedIn(){
    if(firebaseAuth.currentUser != null) {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }




}
    // helper function to update the users profile with their name
    private fun updateUsersName(name: String, auth: FirebaseAuth): UserProfileChangeRequest {
        return  userProfileChangeRequest {
            displayName = name
        }
    }


    override fun onStart() {
        super.onStart()
        Timber.i( "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i( "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i( "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i( "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i( "onRestart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i( "onStop called")
    }



}