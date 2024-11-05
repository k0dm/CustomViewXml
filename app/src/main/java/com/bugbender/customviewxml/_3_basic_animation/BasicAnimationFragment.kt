package com.bugbender.customviewxml._3_basic_animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Half.toFloat
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bugbender.customviewxml.R
import kotlinx.coroutines.NonCancellable.start

class BasicAnimationFragment : Fragment(R.layout.fragment_basic_animation) {

    private lateinit var parentView: View
    private lateinit var animatedView: BasicCircleAnimatedView
    private lateinit var objectAnimator: ObjectAnimator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentView = view
        animatedView = view.findViewById(R.id.animatedView)
    }

    override fun onResume() {
        super.onResume()
        animatedView.startAnimation(500)

        Handler(Looper.getMainLooper()).post {
            val animationAmplitude = (parentView.height.toFloat() - animatedView.height) / 2
            objectAnimator = ObjectAnimator.ofFloat(
                animatedView,
                "translationY",
                animationAmplitude,
                -animationAmplitude
            ).apply {
                interpolator = LinearInterpolator()
                duration = 2000
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
                setCurrentFraction(0.5f)
                start()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        animatedView.stopAnimation()
        objectAnimator.cancel()
    }
}