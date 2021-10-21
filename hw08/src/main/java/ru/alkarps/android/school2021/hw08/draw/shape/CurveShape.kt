package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class CurveShape(
    private val startX: Float,
    private val startY: Float,
    private val color: Int
) : DrawableShape {
    private val path = Path()

    override fun setSecondPoint(x: Float, y: Float) {
        path.reset()
        path.moveTo(startX, startY)
        path.quadTo(calcControlCoordinate(startX, x), calcControlCoordinate(startY, y), x, y)
    }

    private fun calcControlCoordinate(startX: Float, endX: Float) =
        startX * (1 + endX.compareTo(startX) * 0.3F)

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawPath(path, paint)
    }
}