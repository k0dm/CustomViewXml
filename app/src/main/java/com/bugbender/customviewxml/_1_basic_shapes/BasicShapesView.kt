package com.bugbender.customviewxml._1_basic_shapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.bugbender.customviewxml.main.DpToPx.dpToPx

class BasicShapesView : View {

    private var circleXCenter = 0f
    private var circleYCenter = 0f
    private var circleRadius = 0f

    private var rectangle = RectF()

    private val paint = Paint()

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
        val marginInPx = dpToPx(10)
        circleXCenter = w.toFloat() / 2
        circleYCenter = h.toFloat() / 2
        circleRadius = dpToPx(50)

        rectangle = RectF(
            marginInPx + 100f, // +100 pixel
            marginInPx,
            +width.toFloat() - marginInPx,
            +height.toFloat() - marginInPx,
        )
    }

    override fun onDraw(canvas: Canvas) {

        // Draw rectangle
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        paint.color = Color.BLUE

        canvas.drawRect(rectangle, paint)

        // Draw circle
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(circleXCenter, circleYCenter, circleRadius, paint)

        // Draw 2 lines
        paint.color = Color.RED
        canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawLine(width.toFloat(), 0f, 0f, height.toFloat(), paint)
    }
}

