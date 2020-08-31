package com.krisko.numbers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krisko.numbers.data.AppDatabase
import com.krisko.numbers.data.SudokuStats
import com.krisko.numbers.data.SudokuStatsRepository

import kotlinx.coroutines.launch

class HistoryViewModel (application: Application) : AndroidViewModel(application){

    private val repository: SudokuStatsRepository
    val statsData: LiveData<List<SudokuStats>>

    init{
        val sudokuStatsDao = AppDatabase.getDatabase(application, viewModelScope).sudokuStatsDao()
        repository = SudokuStatsRepository(sudokuStatsDao)
        statsData = repository.allData
    }

    fun insert(sudokuStats: SudokuStats) = viewModelScope.launch {
        repository.insert(sudokuStats)
    }

    fun delete(sudokuStats: SudokuStats) = viewModelScope.launch {
        repository.delete(sudokuStats)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}