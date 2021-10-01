package ru.alkarps.android.school2021.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val mainDiagonal = arrayOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))
    private val secondDiagonal = arrayOf(Pair(2, 0), Pair(1, 1), Pair(0, 2))
    private var winCounter = 0
    private var defeatCounter = 0
    private lateinit var winCounterText: TextView
    private lateinit var winCounterImage: ImageView
    private lateinit var defeatCounterText: TextView
    private lateinit var defeatCounterImage: ImageView
    private lateinit var battlefield: Array<Array<ImageView>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWinCounterElements()
        initDefeatCounterElements()
        initBattlefield()
    }

    private fun initWinCounterElements() {
        winCounterText = findViewById(R.id.win_counter_text)
        winCounterImage = findViewById(R.id.win_counter_image)
        updateWinCounter()
    }

    private fun updateWinCounter() {
        updateCounter(winCounter, winCounterText, winCounterImage)
    }

    private fun updateCounter(counter: Int, text: TextView, image: ImageView) {
        text.text = counter.toString()
        image.setImageLevel((counter.toFloat() / 3 * 10000).toInt())
    }

    private fun initDefeatCounterElements() {
        defeatCounterText = findViewById(R.id.defeat_counter_text)
        defeatCounterImage = findViewById(R.id.defeat_counter_image)
        updateDefeatCounter()
    }

    private fun updateDefeatCounter() {
        updateCounter(defeatCounter, defeatCounterText, defeatCounterImage)
    }

    private fun initBattlefield() {
        battlefield = arrayOf(
            arrayOf(
                getImageViewWithListener(R.id.left_top_position),
                getImageViewWithListener(R.id.center_top_position),
                getImageViewWithListener(R.id.right_top_position)
            ),
            arrayOf(
                getImageViewWithListener(R.id.left_center_position),
                getImageViewWithListener(R.id.center_center_position),
                getImageViewWithListener(R.id.right_center_position)
            ),
            arrayOf(
                getImageViewWithListener(R.id.left_bottom_position),
                getImageViewWithListener(R.id.center_bottom_position),
                getImageViewWithListener(R.id.right_bottom_position)
            ),
        )
    }

    private fun getImageViewWithListener(id: Int): ImageView {
        val view = findViewById<ImageView>(id)
        view.apply {
            this.isEnabled = true
            this.isSelected = false
        }
        view.setOnClickListener {
            if (winCounter == 3) {
                winCounter = 0
                updateWinCounter()
            }
            if (defeatCounter == 3) {
                defeatCounter = 0
                updateDefeatCounter()
            }
            if (checkAndSetPressedStatus(it as ImageView)) {
                val playerStepPosition = getCurrentPosition(it.id)
                if (checkOnPlayerWin(playerStepPosition)) {
                    val computerStepPosition = getNextComputerStep()
                    computerStepPosition?.also { checkOnPlayerDefeat(computerStepPosition) }
                        ?: cleanBattlefieldIfDraw()
                }
            }
        }
        return view
    }

    private fun cleanBattlefieldIfDraw() {
        val draw = battlefield.all { it.all { !it.isEnabled } }
        if (draw) cleanBattlefield()
    }

    private fun checkAndSetPressedStatus(image: ImageView): Boolean {
        return if (image.isEnabled) {
            image.isSelected = true
            image.isEnabled = false
            true
        } else false
    }

    private fun getCurrentPosition(id: Int): Pair<Int, Int> {
        for ((rowIndex, row) in battlefield.withIndex()) {
            for ((columnIndex, column) in row.withIndex()) {
                if (column.id == id) return Pair(rowIndex, columnIndex)
            }
        }
        throw IllegalStateException()
    }

    private fun checkOnPlayerWin(position: Pair<Int, Int>): Boolean {
        val win = checkOnWin(position) { !it.isEnabled && it.isSelected }
        if (win) {
            winCounter++
            updateWinCounter()
            cleanBattlefield()
        }
        return !win
    }

    private fun checkOnPlayerDefeat(position: Pair<Int, Int>) {
        val win = checkOnWin(position) { !it.isEnabled && !it.isSelected }
        if (win) {
            defeatCounter++
            updateDefeatCounter()
            cleanBattlefield()
        }
    }

    private fun cleanBattlefield() {
        battlefield.forEach {
            it.forEach {
                it.apply {
                    it.isEnabled = true
                    it.isSelected = false
                }
            }
        }
    }

    private fun checkOnWin(position: Pair<Int, Int>, predicate: (ImageView) -> Boolean): Boolean {
        var win = battlefield[position.first].all(predicate)
        win = win || battlefield.map { it[position.second] }.all(predicate)
        win = win || if (mainDiagonal.contains(position))
            mainDiagonal.map { battlefield[it.first][it.second] }.all(predicate)
        else false
        return win || if (secondDiagonal.contains(position))
            secondDiagonal.map { battlefield[it.first][it.second] }.all(predicate)
        else false
    }

    private fun getNextComputerStep(): Pair<Int, Int>? {
        val step = findNextComputerStep()?.also {
            battlefield[it.first][it.second].apply { this.isEnabled = false }
        }
        return step
    }

    //0. Проверить середину. Если свободна - занять
    //1. Проверить можетли победить комп. Если да - надо завершить
    //2. Проверить можетли победить соперник. Если да - надо помешать
    //3. Впротивном случае найти предыдущий ход, найти свободную линию, сделать ход
    private fun findNextComputerStep(): Pair<Int, Int>? {
        if (battlefield[1][1].isEnabled) return Pair(1, 1)
        return checkCanComputerWinOnThisStep()
            ?: checkCanPlayerWinOnNextStep()
            ?: calculateNextNextComputerStep()
    }

    private fun checkCanComputerWinOnThisStep(): Pair<Int, Int>? {
        return checkCanWin { !it.isSelected }
    }

    private fun checkCanPlayerWinOnNextStep(): Pair<Int, Int>? {
        return checkCanWin { it.isSelected }
    }

    private fun checkCanWin(check: (ImageView) -> Boolean): Pair<Int, Int>? {
        for (i in battlefield.indices) {
            val rowFreeCount = battlefield[i].count { it.isEnabled }
            if (rowFreeCount in 1..2) {
                when (battlefield[i].count { !it.isEnabled && check.invoke(it) }) {
                    1 -> {
                        val j = battlefield[i].withIndex()
                            .find { !it.value.isEnabled && check.invoke(it.value) }!!.index
                        val column = battlefield.map { it[j] }
                        val columnFreeCount = column.count { it.isEnabled }
                        val columnComputerCount = column.count { !it.isEnabled && check.invoke(it) }
                        if (columnFreeCount + columnComputerCount == 3 && columnComputerCount == 2) {
                            return Pair(
                                column.withIndex().find { it.value.isEnabled }!!.index,
                                j
                            )
                        }
                    }
                    2 -> {
                        return Pair(
                            i,
                            battlefield[i].withIndex().find { it.value.isEnabled }!!.index
                        )
                    }
                }
            }
        }
        return null
    }

    private fun calculateNextNextComputerStep(): Pair<Int, Int>? {
        for ((indexRow, row) in battlefield.withIndex()) {
            val index = row.withIndex().find { !it.value.isEnabled && it.value.isSelected }?.index
                ?: continue
            if (row.count { it.isEnabled } == 2) {
                return Pair(indexRow, if (index == 2) 0 else index + 1)
            }
            val column = battlefield.map { it[index] }
            if (column.count { it.isEnabled } == 2) {
                return Pair(if (indexRow == 2) 0 else indexRow + 1, index)
            }
        }
        for ((indexRow, row) in battlefield.withIndex()) {
            val index = row.withIndex().find { it.value.isEnabled }?.index
                ?: continue
            return Pair(indexRow, index)
        }
        return null
    }
}