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
    private val paint = Paint().apply {
        isAntiAlias = true
        this.strokeWidth = 10F
        this.style = Paint.Style.STROKE
    }
    private val drawnShapes = mutableListOf<DrawableShape>()
    private val currentShapes: MutableMap<Int, DrawableShape> = mutableMapOf()
    private var currentShapeFactory: DrawableShapeFactory = EnumShapeFactory.LINE
    private var currentColor: Int = Color.CYAN

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointId = event.getPointerId(pointerIndex)

        Log.i(TAG, "Income action: $action")
        val x = event.getX(pointerIndex)
        val y = event.getY(pointerIndex)
        return when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val shape = currentShapeFactory.newShape(x, y, currentColor)
                currentShapes[pointId] = shape
                drawnShapes.add(shape)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    currentShapes[event.getPointerId(i)]?.setSecondPoint(
                        event.getX(i), event.getY(i)
                    )
                }
                invalidate()
                true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_POINTER_UP -> {
                val shape = currentShapes.remove(pointId)
                shape?.setSecondPoint(x, y)
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }

    }

    override fun onDraw(canvas: Canvas) {
        drawnShapes.forEach { it.draw(canvas, paint) }
    }

    fun reset() {
        drawnShapes.clear()
        invalidate()
    }

    fun changeShape(newFactory: DrawableShapeFactory) {
        currentShapeFactory = newFactory
    }

    fun changePaintColor(color: Int) {
        currentColor = color
    }

    companion object {
        const val TAG = "DrawView"
    }
}