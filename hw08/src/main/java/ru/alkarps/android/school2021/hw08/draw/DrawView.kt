package ru.alkarps.android.school2021.hw08.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class DrawView(context: Context?, attributeSet: AttributeSet?) :
    View(context, attributeSet) {
    private val mPaint = Paint().apply {
        isAntiAlias = true
    }
    private val mPath = Path()

    init {
        setUpPaint()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        Log.i(TAG, "Income action: $action")
        val x = event.x
        val y = event.y
        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(x, y)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.lineTo(x, y)
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint)
    }

    fun reset() {
        mPath.reset()
        invalidate()
    }

    private fun setUpPaint(color: Int = Color.CYAN, strokeWidth: Float = 10F) {
        mPaint.apply {
            this.color = color
            this.strokeWidth = strokeWidth
            this.style = Paint.Style.STROKE
        }
    }

    companion object{
        const val TAG = "DrawView"
    }
}