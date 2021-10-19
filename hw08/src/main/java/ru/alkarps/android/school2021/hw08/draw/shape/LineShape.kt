package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class LineShape(
    private val startX: Float,
    private val startY: Float,
    private val color: Int
) : DrawableShape {
    private var stopX: Float = 0F
    private var stopY: Float = 0F

    override fun setSecondPoint(x: Float, y: Float) {
        stopX = x
        stopY = y
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }
}