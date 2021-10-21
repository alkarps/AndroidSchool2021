package ru.alkarps.android.school2021.hw08.draw

import android.view.ScaleGestureDetector
import kotlin.math.max
import kotlin.math.min

class ScaleDetector(
    private val setCurrentScale: (Float) -> Unit
) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
    private var currentScale = 1F

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        currentScale = max(0.5F, min(detector.scaleFactor, 2F))
        setCurrentScale(currentScale)
        return super.onScale(detector)
    }
}