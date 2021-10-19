package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class CurveShape : DrawableShape {
    val path = Path()
    private var startX: Float = 0F
    private var startY: Float = 0F
    override fun setFirstPoint(x: Float, y: Float) {
        startX = x
        startY = y
        path.moveTo(startX, startY)
    }

    override fun setSecondPoint(x: Float, y: Float) {
        path.quadTo(startX, startY, x, y)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }
}