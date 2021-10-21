package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class PenShape(
    startX: Float,
    startY: Float,
    private val color: Int
) : DrawableShape {
    private val path = Path().apply {
        moveTo(startX, startY)
    }

    override fun setSecondPoint(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawPath(path, paint)
    }
}