package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class RectShape(
    private val startX: Float,
    private val startY: Float,
    private val color: Int
) : DrawableShape {
    private var rectF = RectF()

    override fun setSecondPoint(x: Float, y: Float) {
        rectF.set(startX, startY, x, y)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawRect(rectF, paint)
    }
}