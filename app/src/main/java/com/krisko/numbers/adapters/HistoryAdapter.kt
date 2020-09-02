package com.krisko.numbers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisko.numbers.R
import com.krisko.numbers.data.SudokuStats
import com.krisko.numbers.utils.SudokuCanvasView
import java.lang.StringBuilder
import java.text.DateFormat
import java.util.*

class HistoryAdapter(locale: Locale) : RecyclerView.Adapter<HistoryAdapter.StatsViewHolder>() {

    private var statsList = emptyList<SudokuStats>()
    private val dateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale)

    inner class StatsViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val historyTextView: TextView = v.findViewById(R.id.history_textview)
        val historySudokuView: SudokuCanvasView = v.findViewById(R.id.history_sudokuview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsViewHolder {
        return StatsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter_history, parent, false))
    }

    override fun onBindViewHolder(
        holder: StatsViewHolder,
        position: Int
    ) {
        val statsItem = statsList[position]
        val timeMinute = statsItem.completedTime?.div(60)
        val timeSecond = statsItem.completedTime?.minus(statsItem.completedTime.div(60) * 60)
        val stringBuilder = StringBuilder()
        if(timeMinute == 1){
            stringBuilder.append(timeMinute.toString() + " Minute ")
        }else if(timeMinute != 0) {
            stringBuilder.append(timeMinute.toString() + " Minutes ")
        }
        if(timeSecond == 1){
            stringBuilder.append(timeSecond.toString() + " Second")
        }else if(timeSecond != 0){
            stringBuilder.append(timeSecond.toString() + " Seconds")
        }
        val date: String? = dateFormat.format(statsItem.date?.let { Date(it) })
        holder.historyTextView.text = "Difficulty: ${getDifficultyString(statsItem.difficulty)} // ${statsItem.clues} clues\nCompleted in: $stringBuilder\nDate: $date"
        holder.historySudokuView.grid = statsItem.grid

    }

    internal fun setStats(stats: List<SudokuStats>){
        this.statsList = stats
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return statsList.size
    }

    private fun getDifficultyString(difficulty: Int?) : String{
        when(difficulty){
            0 -> return "I'M TOO YOUNG TO DIE"
            1 -> return "HURT ME PLENTY"
            2 -> return "ULTRA-VIOLENCE"
            3 -> return "NIGHTMARE"
            4 -> return "ULTRA-NIGHTMARE"
            404 -> return "TEST"
            -1 -> return "DEFAULT DIFFICULTY"
            else -> return "???"
        }
    }
}