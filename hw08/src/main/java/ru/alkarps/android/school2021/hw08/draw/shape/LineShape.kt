package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class LineShape : DrawableShape {
    private var startX: Float = 0F
    private var startY: Float = 0F
    private var stopX: Float = 0F
    private var stopY: Float = 0F

    override fun setFirstPoint(x: Float, y: Float) {
        startX = x
        startY = y
    }

    override fun setSecondPoint(x: Float, y: Float) {
        stopX = x
        stopY = y
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }
}