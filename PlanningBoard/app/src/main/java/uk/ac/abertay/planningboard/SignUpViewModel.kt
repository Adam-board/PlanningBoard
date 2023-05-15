package uk.ac.abertay.planningboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    private val _usernameSignUp = MutableLiveData<String>()
            val usernameSignUp : LiveData<String> get() = _usernameSignUp

    private val _emailAddressSignUp = MutableLiveData<String>()
            val emailAddressSignUp : LiveData<String> get() = _emailAddressSignUp

    private val _passwordSignUp = MutableLiveData<String>()
            val passwordSignUp : LiveData<String> get() = _passwordSignUp

    private val _passwordReType = MutableLiveData<String>()
            val passwordReType : LiveData<String> get() = _passwordReType

}

