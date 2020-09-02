package com.krisko.numbers.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisko.numbers.R
import com.krisko.numbers.adapters.HistoryAdapter
import com.krisko.numbers.viewmodel.HistoryViewModel
import com.krisko.numbers.viewmodel.MainSharedViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import java.lang.Exception

private const val FRAGMENT_ID = 3

class HistoryFragment : Fragment() {

    private lateinit var adapter: HistoryAdapter
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var sharedViewModel: MainSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(resources.configuration.locale)
        recycler_history.adapter = adapter
        recycler_history.layoutManager = LinearLayoutManager(activity)

        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        historyViewModel.data.observe(viewLifecycleOwner, Observer{ stats ->
            stats?.let{adapter.setStats(it)}
        })

        sharedViewModel = activity?.run{
            ViewModelProvider(this).get(MainSharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        sharedViewModel.currentFragment = FRAGMENT_ID
    }
}
