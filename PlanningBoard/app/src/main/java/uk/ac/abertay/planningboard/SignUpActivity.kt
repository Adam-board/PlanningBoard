package uk.ac.abertay.planningboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
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

        binding.CreateAccount.setOnClickListener {
            val emailAddressSignup = binding.editTextEmailAddressSignUp.text.toString()
            val pass = binding.editTextPasswordSignUp.text.toString()
            val confirmPass = binding.editTextRetypePassword.text.toString()

            if (emailAddressSignup.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if (pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(emailAddressSignup, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{

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




}