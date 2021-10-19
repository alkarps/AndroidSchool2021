package ru.alkarps.android.school2021.hw08.draw.factory

import ru.alkarps.android.school2021.hw08.draw.DrawableShape
import ru.alkarps.android.school2021.hw08.draw.DrawableShapeFactory
import ru.alkarps.android.school2021.hw08.draw.shape.CurveShape
import ru.alkarps.android.school2021.hw08.draw.shape.LineShape
import ru.alkarps.android.school2021.hw08.draw.shape.RectShape

enum class EnumShapeFactory(private val init: (x: Float, y: Float) -> DrawableShape) :
    DrawableShapeFactory {
    LINE(::LineShape),
    RECT(::RectShape),
    CURVE(::CurveShape);

    override fun newShape(x: Float, y: Float): DrawableShape = init(x, y)
}