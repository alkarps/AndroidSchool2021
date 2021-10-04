package ru.alkarps.android.school2021.hw06.repository

import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.Quantity
import ru.alkarps.android.school2021.hw06.model.QuantityUnit

class Repository {
    val quantities = listOf(
        Quantity(R.string.speed, listOf(
            QuantityUnit(R.string.speed_m_in_s, 1.0),
            QuantityUnit(R.string.speed_m_in_m, 0.0167),
            QuantityUnit(R.string.speed_m_in_h, 0.000277778),
            QuantityUnit(R.string.speed_km_in_s, 1000.0),
            QuantityUnit(R.string.speed_km_in_m, 16.666667),
            QuantityUnit(R.string.speed_km_in_h, 0.277778),
            QuantityUnit(R.string.speed_ml_in_s, 1609.3439902547),
            QuantityUnit(R.string.speed_ml_in_m, 26.822399837578),
            QuantityUnit(R.string.speed_ml_in_h, 0.44704000161)
        )),
        Quantity(R.string.volume, listOf(
            QuantityUnit(R.string.volume_m, 1.0),
            QuantityUnit(R.string.volume_dm, 0.001),
            QuantityUnit(R.string.volume_sm, 0.000001),
            QuantityUnit(R.string.volume_mm, 0.000000001),
            QuantityUnit(R.string.volume_l, 0.001),
            QuantityUnit(R.string.volume_ft, 0.0283168),
            QuantityUnit(R.string.volume_yd, 0.000016387),
            QuantityUnit(R.string.volume_b, 0.158987)
        )),
        Quantity(R.string.distance, listOf(
            QuantityUnit(R.string.distance_m, 1.0),
            QuantityUnit(R.string.distance_km, 1000.00),
            QuantityUnit(R.string.distance_sm, 0.01),
            QuantityUnit(R.string.distance_dm, 0.1),
            QuantityUnit(R.string.distance_mm, 0.001),
            QuantityUnit(R.string.distance_ml, 1482.0),
            QuantityUnit(R.string.distance_ft, 0.3048 ),
            QuantityUnit(R.string.distance_yd, 0.9144),
            QuantityUnit(R.string.distance_d, 0.0254)
        ))
    )
}