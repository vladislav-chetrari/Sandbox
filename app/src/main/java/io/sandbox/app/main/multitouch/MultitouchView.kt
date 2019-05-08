package io.sandbox.app.main.multitouch

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultitouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val pointers = arrayListOf<Point>()
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        isAntiAlias = true
        strokeWidth = 1.dp
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)
        pointers.clear()
        pointers.addAll((0 until event.pointerCount).map { Point(event.getX(it).toInt(), event.getY(it).toInt()) })
        postInvalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.drawColor(Color.BLACK)
        pointers.forEach { point ->
            canvas.run {
                drawLine(0f, point.y.toFloat(), width.toFloat(), point.y.toFloat(), paint)
                drawLine(point.x.toFloat(), 0f, point.x.toFloat(), height.toFloat(), paint)
                drawCircle(point.x.toFloat(), point.y.toFloat(), 24.dp, paint)
            }
        }
    }

    private val Int.dp: Float
        get() = times(resources.displayMetrics.density)
}