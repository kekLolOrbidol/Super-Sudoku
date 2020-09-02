package com.krisko.numbers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krisko.numbers.data.AppDatabase
import com.krisko.numbers.data.SudokuStats
import com.krisko.numbers.data.SudokuStatsRepository


class RecordsViewModel (application: Application) : AndroidViewModel(application){

    private val repository: SudokuStatsRepository
    val data: LiveData<List<SudokuStats>>

    init{
        val sudokuStatsDao = AppDatabase.getDatabase(application, viewModelScope).sudokuStatsDao()
        repository = SudokuStatsRepository(sudokuStatsDao)
        data = repository.recordData
    }
}