package ru.alkarps.android.school2021.hw13

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import ru.alkarps.android.school2021.hw13.fragment.EditFragment
import ru.alkarps.android.school2021.hw13.fragment.TextFragment

class MainActivity : AppCompatActivity(), PublicApi {
    private lateinit var root: ViewGroup
    private var textFragment: TextFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        root = findViewById(R.id.main_root)
    }

    override fun showTextFragment() {
        if (textFragment == null) {
            val text = supportFragmentManager.findFragmentById(R.id.edit_fragment) as EditFragment
            val viewGroup = FragmentContainerView(this)
            viewGroup.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            viewGroup.id = R.id.text_fragment_id
            root.addView(viewGroup)

            textFragment = TextFragment.newInstance(text.getText())
            supportFragmentManager.beginTransaction()
                .add(R.id.text_fragment_id, textFragment!!)
                .commit()
        }
    }

    override fun updateTextFragment(text: String) {
        textFragment?.setText(text)
    }
}