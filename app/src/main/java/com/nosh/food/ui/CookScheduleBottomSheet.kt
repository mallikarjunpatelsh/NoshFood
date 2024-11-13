package com.nosh.food.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nosh.food.R
import com.nosh.food.databinding.LayoutScheduleBottomSheetBinding

class CookScheduleBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: LayoutScheduleBottomSheetBinding

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogRoundedCorners
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutScheduleBottomSheetBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog

        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.close.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
//        dialog?.setOnShowListener {
            val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            val height = context?.resources?.displayMetrics?.heightPixels
        if (height != null) {
            bottomSheetBehavior.peekHeight = (height * 0.80).toInt()
        }
//        }
    }

    companion object {
        const val TAG = "CookScheduleBottomSheet"
    }
}