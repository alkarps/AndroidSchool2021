package ru.alkarps.android.school2021.hw08.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import ru.alkarps.android.school2021.hw08.draw.factory.EnumShapeFactory

class DrawView(context: Context?, attributeSet: AttributeSet?) :
    View(context, attributeSet) {
    private val mPaint = Paint().apply {
        isAntiAlias = true
    }
    private val drawnShapes = mutableListOf<DrawableShape>()
    private lateinit var currentShape: DrawableShape
    private var currentShapeFactory: DrawableShapeFactory = EnumShapeFactory.LINE

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
                currentShape = currentShapeFactory.newShape(x, y)
                drawnShapes.add(currentShape)
                true
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                currentShape.setSecondPoint(x, y)
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }

    }

    override fun onDraw(canvas: Canvas) {
        drawnShapes.forEach { it.draw(canvas, mPaint) }
    }

    fun reset() {
        drawnShapes.clear()
        invalidate()
    }

    fun changeShape(newFactory: DrawableShapeFactory) {
        currentShapeFactory = newFactory
    }

    private fun setUpPaint(color: Int = Color.CYAN, strokeWidth: Float = 10F) {
        mPaint.apply {
            this.color = color
            this.strokeWidth = strokeWidth
            this.style = Paint.Style.STROKE
        }
    }

    companion object {
        const val TAG = "DrawView"
    }
}