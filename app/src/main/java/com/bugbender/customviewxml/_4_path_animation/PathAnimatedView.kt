package com.bugbender.customviewxml._4_path_animation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.bugbender.customviewxml.main.DpToPx.dpToPx

class PathAnimatedView : View {

    private val trianglePath = Path()
    private val animatedTrianglePath = Path()
    private val trianglePaint = Paint()

    private lateinit var valueAnimator: ValueAnimator

    private val bordersRectangle = Rect()
    private val bordersPaint = Paint()

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


    fun startAnimation(durationInMs: Long) {
        valueAnimator = ValueAnimator.ofFloat(0f, 2f).apply {
            interpolator = LinearInterpolator()
            duration = durationInMs
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                updateTrianglePath(it.animatedValue as Float)
            }
            start()
        }
    }

    fun stopAnimation() {
        valueAnimator.cancel()
    }

    fun updateTrianglePath(fraction: Float) {
        val pathMeasure = PathMeasure(trianglePath, false)
        animatedTrianglePath.reset()
        if (fraction <= 1) {
            pathMeasure.getSegment(0f, pathMeasure.length * fraction, animatedTrianglePath, true)
        } else {
            pathMeasure.getSegment(pathMeasure.length * (fraction -1), pathMeasure.length, animatedTrianglePath,true)
        }

        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Borders setup
        bordersRectangle.left = 0
        bordersRectangle.right = w
        bordersRectangle.top = 0
        bordersRectangle.bottom = h

        bordersPaint.color = Color.RED
        bordersPaint.style = Paint.Style.STROKE
        bordersPaint.strokeWidth = dpToPx(1)
        bordersPaint.pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)

        // Triangle setup
        val strokeWidth = dpToPx(10)
        val padding = strokeWidth / 2

        val topVertex = PointF(w.toFloat() / 2, 0f + padding)
        val rightVertex = PointF(w.toFloat() - padding, h.toFloat() - padding)
        val leftVertex = PointF(0f + padding, h.toFloat() - padding)

        trianglePath.reset()
        trianglePath.moveTo(topVertex.x, topVertex.y)
        trianglePath.lineTo(rightVertex.x, rightVertex.y)
        trianglePath.lineTo(leftVertex.x, leftVertex.y)
        trianglePath.lineTo(topVertex.x, topVertex.y)
        //trianglePath.close()

        trianglePaint.color = Color.BLUE
        trianglePaint.style = Paint.Style.STROKE
        trianglePaint.strokeWidth = strokeWidth
        trianglePaint.isAntiAlias = true
        trianglePaint.strokeJoin = Paint.Join.ROUND
        trianglePaint.strokeCap = Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(bordersRectangle, bordersPaint)
        canvas.drawPath(animatedTrianglePath, trianglePaint)
    }
}