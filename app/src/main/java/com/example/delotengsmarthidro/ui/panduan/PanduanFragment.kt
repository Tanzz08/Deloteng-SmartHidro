package com.example.delotengsmarthidro.ui.panduan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delotengsmarthidro.adapter.TipsListAdapter
import com.example.delotengsmarthidro.adapter.TutorListAdapter
import com.example.delotengsmarthidro.data.list.tips.DummyTipsData
import com.example.delotengsmarthidro.data.list.tutorial.DummyTutorialData
import com.example.delotengsmarthidro.databinding.FragmentPanduanBinding

class PanduanFragment : Fragment() {

    private var _binding: FragmentPanduanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menghapus ViewModelProvider yang tidak terpakai agar lebih bersih
        _binding = FragmentPanduanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup untuk Tutorial RecyclerView
        val tutorialData = DummyTutorialData.getTutorialSteps()
        val tutorialAdapter = TutorListAdapter(tutorialData)
        // Panggil fungsi helper baru kita
        setupRecyclerView(binding.rvTuorial, tutorialAdapter)

        // 2. Setup untuk Tips RecyclerView
        val tipsData = DummyTipsData.getTipsSolution()
        val tipsAdapter = TipsListAdapter(tipsData)
        // Panggil fungsi helper yang sama
        // MEMPERBAIKI BUG: Menggunakan binding.rvTips, bukan rvTuorial
        setupRecyclerView(binding.rvTips, tipsAdapter)
    }

    /**
     * Fungsi helper baru untuk menghindari pengulangan kode.
     * Fungsi ini mengatur LayoutManager, Adapter, dan NestedScrolling
     * untuk RecyclerView yang diberikan.
     */
    private fun setupRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
