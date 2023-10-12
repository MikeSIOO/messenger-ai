package com.example.messenger_ai.dialogues.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger_ai.R
import com.example.messenger_ai.appComponent
import com.example.messenger_ai.utils.Status
import com.example.messenger_ai.dialogues.ui.recyclerview.DialoguesAdapter
import com.example.messenger_ai.dialogues.ui.viewmodel.DialoguesViewModel
import com.example.messenger_ai.dialogues.ui.viewmodel.DialoguesViewModelFactory
import javax.inject.Inject

class DialoguesFragment : Fragment(R.layout.fragment_dialogues) {
    private val dialoguesViewModel: DialoguesViewModel by viewModels {
        factory.create()
    }

    @Inject
    lateinit var factory: DialoguesViewModelFactory.Factory

    private lateinit var dialoguesRecyclerView: RecyclerView
    private lateinit var load: ProgressBar
    private lateinit var error: TextView

    private val dialoguesAdapter = DialoguesAdapter { it ->
        findNavController().navigate(
            DialoguesFragmentDirections.actionDialoguesFragmentToChatFragment(
                it
            )
        )
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialoguesViewModel.getDialogues()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialoguesRecyclerView = view.findViewById<RecyclerView>(R.id.dialoguesRecyclerView)
        load = view.findViewById<ProgressBar>(R.id.load)
        error = view.findViewById<TextView>(R.id.error)

        dialoguesRecyclerView.adapter = dialoguesAdapter
        dialoguesRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        dialoguesViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                Status.LOADING -> {
                    load.isVisible = true
                    error.isVisible = false
                    error.setOnClickListener(null)
                }

                Status.SUCCESS -> {
                    load.isVisible = false
                    error.isVisible = false
                    error.setOnClickListener(null)
                }

                Status.ERROR -> {
                    load.isVisible = false
                    error.isVisible = true
                    error.setOnClickListener {
                        dialoguesViewModel.getDialogues()
                    }
                }
            }
        }

        dialoguesViewModel.dialogues.observe(viewLifecycleOwner) {
            dialoguesAdapter.dialogues = it.results
            dialoguesAdapter.notifyDataSetChanged()
        }
    }
}
