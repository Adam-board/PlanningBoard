package uk.ac.abertay.planningboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import uk.ac.abertay.planningboard.databinding.LandingFragmentBinding

class loginPage: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val binding: LandingFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.login_fragment, container, false
    )
        return binding.root
    }


}