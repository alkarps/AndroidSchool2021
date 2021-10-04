package ru.alkarps.android.school2021.hw06.converter

import androidx.recyclerview.widget.DiffUtil
import ru.alkarps.android.school2021.hw06.model.ConverterValue

class ConverterDiffUtilCallback(
    private val old: List<ConverterValue>,
    private val new: List<ConverterValue>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}