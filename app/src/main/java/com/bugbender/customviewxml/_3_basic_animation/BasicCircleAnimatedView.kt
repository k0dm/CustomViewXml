package com.bugbender.customviewxml._3_basic_animation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.bugbender.customviewxml.main.DpToPx.dpToPx

class BasicCircleAnimatedView : View {

    private var circleXCenter = 0f
    private var circleYCenter = 0f
    private var circleRadius = 0f

    private var rectangle = RectF()

    private val paint = Paint()

    private lateinit var valueAnimator: ValueAnimator

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        circleXCenter = w.toFloat() / 2
        circleYCenter = w.toFloat() / 2
        circleRadius = dpToPx(10)

        rectangle.left = 0f
        rectangle.right = width.toFloat()
        rectangle.top = 0f
        rectangle.bottom = height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        canvas.drawRect(rectangle, paint)

        paint.style = Paint.Style.FILL
        canvas.drawCircle(circleXCenter, circleYCenter, circleRadius, paint)
    }

    fun startAnimation(durationInMs: Long) {
        valueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            interpolator = LinearInterpolator()
            duration = durationInMs
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            setCurrentFraction(0.5f)
            addUpdateListener {
                circleXCenter =
                    circleRadius + it.animatedValue as Float * (rectangle.right - rectangle.left - circleRadius * 2)
                invalidate()
            }
            start()
        }
    }

    fun stopAnimation() {
        valueAnimator.cancel()
    }
}