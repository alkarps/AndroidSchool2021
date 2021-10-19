package ru.alkarps.android.school2021.hw08.draw

import android.graphics.Canvas
import android.graphics.Paint

/**
 * Доступные для рисования фигуры
 *
 */
interface DrawableShape {
    /**
     * Конечная точка фигуры
     *
     * @param x X координата
     * @param y Y координата
     */
    fun setSecondPoint(x: Float, y: Float)

    /**
     * Отрисовка фигуры
     *
     * @param canvas полотно для отрисовки
     * @param paint краска для отрисовки
     */
    fun draw(canvas: Canvas, paint: Paint)
}