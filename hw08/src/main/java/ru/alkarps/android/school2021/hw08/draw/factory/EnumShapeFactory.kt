package ru.alkarps.android.school2021.hw08.draw.factory

import android.graphics.Paint
import ru.alkarps.android.school2021.hw08.draw.DrawableShape
import ru.alkarps.android.school2021.hw08.draw.DrawableShapeFactory
import ru.alkarps.android.school2021.hw08.draw.shape.CurveShape
import ru.alkarps.android.school2021.hw08.draw.shape.LineShape
import ru.alkarps.android.school2021.hw08.draw.shape.RectShape

enum class EnumShapeFactory(
    private val init: (x: Float, y: Float, color: Int) -> DrawableShape
) : DrawableShapeFactory {
    LINE(::LineShape),
    RECT(::RectShape),
    CURVE(::CurveShape);

    override fun newShape(x: Float, y: Float, color: Int): DrawableShape = init(x, y, color)
}