package io.sandbox.app.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.KeyEvent.ACTION_UP
import android.view.MotionEvent
import android.view.View
import io.sandbox.R
import io.sandbox.app.extension.color

class MultitouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val pointers = mutableListOf<Point>()
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        isAntiAlias = true
        strokeWidth = 1.dp
    }
    private val textPaint = Paint().apply {
        style = Paint.Style.FILL
        color = context.color(R.color.colorPrimaryLight)
        isAntiAlias = true
        textSize = 14.sp
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)
        pointers.clear()
        if (event.action != ACTION_UP || event.pointerCount != 1) {
            pointers.addAll((0 until event.pointerCount)
                .map { Point(event.getX(it).toInt(), event.getY(it).toInt()) })
        }
        postInvalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.drawColor(Color.BLACK)
        pointers.forEachIndexed { index, point ->
            point.drawTouchTargetOn(canvas)
            point.drawTouchInfoOn(index, canvas)
        }
    }

    private fun Point.drawTouchTargetOn(canvas: Canvas) {
        canvas.drawLine(0f, y.toFloat(), width.toFloat(), y.toFloat(), paint)
        canvas.drawLine(x.toFloat(), 0f, x.toFloat(), height.toFloat(), paint)
        canvas.drawCircle(x.toFloat(), y.toFloat(), 24.dp, paint)
    }

    private fun Point.drawTouchInfoOn(
        pointIndex: Int,
        canvas: Canvas,
        paddingStart: Float = 16.dp,
        paddingTop: Float = 16.dp,
        textMarginTop: Float = 4.dp
    ) = canvas.drawText(
        "Touch[$pointIndex] = (x: $x, y: $y)",
        paddingStart,
        paddingTop + textPaint.textSize + (textPaint.textSize + textMarginTop) * pointIndex,
        textPaint
    )

    private val Int.dp: Float
        get() = times(resources.displayMetrics.density)

    private val Int.sp: Float
        get() = times(resources.displayMetrics.scaledDensity)
}