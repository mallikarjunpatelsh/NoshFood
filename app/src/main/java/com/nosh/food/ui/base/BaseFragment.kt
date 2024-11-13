package com.nosh.food.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nosh.food.MainActivity
import com.nosh.food.R
import com.nosh.food.data.base.Resource

/**
 * Base fragment
 * Base fragment for all other fragment
 * @constructor Create empty Base fragment
 */
abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            // Handle the back button event
            if (!findNavController().popBackStack()) activity?.finish()
        }
    }

    /**
     * On view created
     * this methods call getBundleParameter(), setObservers(), initViews()
     * so avoiding redudant code in child fragment
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleParameter()
        initViews()
        setObservers()
    }

    abstract fun getBundleParameter()

    abstract fun setObservers()

    abstract fun initViews()

    /**
     * Set loading state
     * This displays the progress bar according to  state
     * @param state
     * @param status
     * @param message
     */
    fun setLoadingState(state: Boolean, status: Resource.Status, message: String?) {
//        (activity as? MainActivity)?.setLoadingState(state)
        if (status == Resource.Status.ERROR) {
            showToast(message)
        }
    }

    /**
     * Show toast
     * displays toast
     * @param message
     */
    fun showToast(message: String?) {
        Toast.makeText(context,
            message?:getString(R.string.something_went_wrong),
            Toast.LENGTH_SHORT
        ).show()
    }
}