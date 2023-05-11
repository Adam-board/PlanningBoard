package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import uk.ac.abertay.planningboard.databinding.SignupFragmentBinding

class SignUpFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: SignupFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.signup_fragment, container, false
    )
        return binding.root
    }

}