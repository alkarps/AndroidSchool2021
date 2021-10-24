package ru.alkarps.android.school2021.hw18.data.holiday.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.holiday.model.DateInfoDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.WeekdayDTO
import ru.alkarps.android.school2021.hw18.domen.model.Holiday

class ImplHolidayConverterTest {
    @Test
    fun fromDto() {
        val holidays = holidaysDTO()
        assertThat(ImplHolidayConverter().fromDto(holidays)).isEqualTo(expected())
    }

    private fun holidaysDTO() = listOf(
        HolidayDTO(
            "First",
            "2020-03-03",
            "2020-03-02",
            false,
            "RU",
            "1231",
            WeekdayDTO(
                DateInfoDTO("qwe", "1"),
                DateInfoDTO("ewq", "2")
            )
        ),
        HolidayDTO(
            "Second",
            "2020-05-03",
            "2020-05-02",
            true,
            "RU",
            "1232",
            WeekdayDTO(
                DateInfoDTO("qwe", "1"),
                DateInfoDTO("ewq", "2")
            )
        )
    )

    fun expected() = listOf(
        Holiday("1231", "First", "qwe 2020-03-03", "ewq 2020-03-02", false),
        Holiday("1232", "Second", "qwe 2020-05-03", "ewq 2020-05-02", true)
    )
}