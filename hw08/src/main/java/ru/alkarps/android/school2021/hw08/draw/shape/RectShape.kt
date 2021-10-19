package ru.alkarps.android.school2021.hw08.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import ru.alkarps.android.school2021.hw08.draw.DrawableShape

class RectShape : DrawableShape {
    private var startX: Float = 0F
    private var startY: Float = 0F
    private var rectF = RectF()

    override fun setFirstPoint(x: Float, y: Float) {
        startX = x
        startY = y
    }

    override fun setSecondPoint(x: Float, y: Float) {
        rectF.set(startX, startY, x, y)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawRect(rectF, paint)
    }
}