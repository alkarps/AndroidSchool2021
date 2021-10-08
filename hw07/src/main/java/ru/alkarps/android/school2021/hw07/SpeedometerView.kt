package ru.alkarps.android.school2021.hw07

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

class SpeedometerView(
    context: Context,
    attributeSet: AttributeSet?
) : View(context, attributeSet) {
    private var speed: Int = 0
    private var maxSpeed: Int = -1

    @ColorInt
    private var colorLowSpeed: Int = Color.GREEN

    @ColorInt
    private var colorMidSpeed: Int = Color.YELLOW

    @ColorInt
    private var colorHighSpeed: Int = Color.RED

    @ColorInt
    private var colorArrow: Int = Color.CYAN

    @ColorInt
    private var colorScale: Int = Color.CYAN

    private val scalePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style =Paint.Style.STROKE
        strokeWidth = 32.0F

    }
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val scaleTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val scaleRect = RectF(0F,0F,500F, 500F)

    init {
        val array = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.SpeedometerView,
            R.attr.speedometerViewDefaultAttr,
            R.style.SpeedometerViewDefaultStyle
        )
        try {
            maxSpeed = array.getInteger(R.styleable.SpeedometerView_maxSpeed, -1)
            colorLowSpeed = array.getColor(R.styleable.SpeedometerView_color_speed_low, Color.GREEN)
            colorMidSpeed =
                array.getColor(R.styleable.SpeedometerView_color_speed_mid, Color.YELLOW)
            colorHighSpeed = array.getColor(R.styleable.SpeedometerView_color_speed_high, Color.RED)
            colorArrow = array.getColor(R.styleable.SpeedometerView_color_arrow, Color.CYAN)
            colorScale = array.getColor(R.styleable.SpeedometerView_color_scale, Color.CYAN)
            scalePaint.color = colorScale
        } finally {
            array.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            it.translate(16F,16F)
            it.drawArc(scaleRect, -225f, 270f,false, scalePaint)
        }
    }
}