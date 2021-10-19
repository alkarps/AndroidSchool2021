package ru.alkarps.android.school2021.hw08.draw

/**
 * Фабрика для создания объектов типа [DrawableShape]
 *
 */
interface DrawableShapeFactory {
    fun newShape(x: Float, y: Float): DrawableShape
}