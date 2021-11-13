package ru.alkarps.android.school2021.hw29.presentation.main

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw29.databinding.ActivityMainBinding
import ru.alkarps.android.school2021.hw29.presentation.add.AddActivity
import ru.alkarps.android.school2021.hw29.presentation.dictionary.DictionaryViewModel
import ru.alkarps.android.school2021.hw29.presentation.dictionary.factory.DictionaryViewModelFactory
import ru.alkarps.android.school2021.hw29.presentation.main.adapter.DictionaryAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DictionaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DictionaryViewModelFactory(applicationContext))
            .get(DictionaryViewModel::class.java)

        binding.addNewItem.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, ADD_ACTIVITY_REQUEST_CODE)
        }
        ItemTouchHelper(ItemTouchHelperCallback()).attachToRecyclerView(binding.words)
        binding.words.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadDataRx()
        viewModel.dictionaryItems.observe(this) {
            binding.words.adapter = DictionaryAdapter(it)
        }
        viewModel.errors.observe(this) {
            Snackbar.make(binding.root, it.toString(), BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_ACTIVITY_REQUEST_CODE) viewModel.loadDataRx()
        else super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val ADD_ACTIVITY_REQUEST_CODE = 1
    }

    private inner class ItemTouchHelperCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        private val background = ColorDrawable()
        private val backgroundColor = Color.parseColor("#f44336")

        override fun onMove(
            rv: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.bindingAdapterPosition
            binding.words.adapter?.getItemId(position)?.apply {
                viewModel.removeWord(this)
            }
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val iv = viewHolder.itemView
            background.color = backgroundColor
            background.setBounds(iv.right + dX.toInt(), iv.top, iv.right, iv.bottom)
            background.draw(c)
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

    }
}