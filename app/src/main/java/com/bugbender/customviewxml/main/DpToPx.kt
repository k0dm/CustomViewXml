package com.bugbender.customviewxml.main

import android.util.TypedValue
import android.view.View

object DpToPx{

    fun View.dpToPx(dp: Number): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        )
    }
}