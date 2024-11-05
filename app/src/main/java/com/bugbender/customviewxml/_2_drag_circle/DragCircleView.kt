package com.bugbender.customviewxml._2_drag_circle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bugbender.customviewxml.main.DpToPx.dpToPx
import kotlin.math.sqrt

class DragCircleView : View {

    private var circleXCenter = 0f
    private var circleYCenter = 0f
    private var circleRadius = 0f

    private var isDragged = false
    private var lastXActionEvent = 0f
    private var lastYActionEvent = 0f

    private var rectangle = RectF()

    private var paint = Paint()

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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN && isCircleTouched(event.x, event.y)) {
            isDragged = true
            lastXActionEvent = event.x
            lastYActionEvent = event.y
        } else if (event.action == MotionEvent.ACTION_MOVE) {

            val dx = event.x - lastXActionEvent
            val dy = event.y - lastYActionEvent

            if (circleYCenter + circleRadius + dy > rectangle.bottom) {
                circleYCenter = rectangle.bottom - circleRadius

                circleXCenter = if (circleXCenter - circleRadius + dx < rectangle.left) {
                    rectangle.left + circleRadius
                } else if (circleXCenter + circleRadius + dx > rectangle.right) {
                    rectangle.right - circleRadius
                } else {
                    circleXCenter + dx
                }
            } else if (circleYCenter - circleRadius + dy < rectangle.top) {
                circleYCenter = rectangle.top + circleRadius

                circleXCenter = if (circleXCenter - circleRadius + dx < rectangle.left) {
                    rectangle.left + circleRadius
                } else if (circleXCenter + circleRadius + dx > rectangle.right) {
                    rectangle.right - circleRadius
                } else {
                    circleXCenter + dx
                }
            } else if (circleXCenter - circleRadius + dx < rectangle.left) {
                circleXCenter = rectangle.left + circleRadius

                circleYCenter = if (circleYCenter + circleRadius + dy > rectangle.bottom) {
                    rectangle.bottom - circleRadius
                } else if (circleYCenter - circleRadius + dy < rectangle.top) {
                    rectangle.top + circleRadius
                } else {
                    circleYCenter + dy
                }
            } else if (circleXCenter + circleRadius + dx > rectangle.right) {
                circleXCenter = rectangle.right - circleRadius

                circleYCenter = if (circleYCenter + circleRadius + dy > rectangle.bottom) {
                    rectangle.bottom - circleRadius
                } else if (circleYCenter - circleRadius + dy < rectangle.top) {
                    rectangle.top + circleRadius
                } else {
                    circleYCenter + dy
                }
            } else {
                circleXCenter += dx
                circleYCenter += dy
            }

            lastXActionEvent = event.x
            lastYActionEvent = event.y

        } else {
            isDragged = false
        }
        invalidate()
        return true
    }

    private fun isCircleTouched(eventX: Float, eventY: Float): Boolean {
        val dx = eventX - circleXCenter
        val dy = eventY - circleYCenter
        return sqrt(dx * dx + dy * dy) <= circleRadius
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        circleXCenter = w.toFloat() / 2
        circleYCenter = h.toFloat() / 2
        circleRadius = dpToPx(30)

        rectangle = RectF(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {

        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        canvas.drawCircle(circleXCenter, circleYCenter, circleRadius, paint)

        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        canvas.drawRect(rectangle, paint)
    }
}