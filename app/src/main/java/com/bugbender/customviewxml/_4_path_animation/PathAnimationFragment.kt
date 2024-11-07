package com.bugbender.customviewxml._4_path_animation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bugbender.customviewxml.R

class PathAnimationFragment() : Fragment(R.layout.fragment_path_animation) {

    private lateinit var animatedView: PathAnimatedView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animatedView = view.findViewById(R.id.pathAnimatedView)
    }

    override fun onResume() {
        super.onResume()
        animatedView.startAnimation(1500)
    }

    override fun onStop() {
        super.onStop()
        animatedView.stopAnimation()
    }
}