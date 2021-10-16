package ru.alkarps.android.school2021.hw14.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw14.R

class DisplayFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    companion object {
        fun newInstance(addOperation: Boolean) = DisplayFragment(
            if (addOperation) R.layout.display_fragment_layout
            else R.layout.display_replace_fragment_layout
        )

    }
}