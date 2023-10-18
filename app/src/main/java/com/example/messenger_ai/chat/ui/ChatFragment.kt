package com.example.messenger_ai.chat.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger_ai.R
import com.example.messenger_ai.appComponent
import com.example.messenger_ai.chat.ui.recyclerview.ChatAdapter
import com.example.messenger_ai.chat.ui.viewmodel.ChatViewModel
import com.example.messenger_ai.chat.ui.viewmodel.ChatViewModelFactory
import com.example.messenger_ai.dialogues.data.DialogueModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private val args: ChatFragmentArgs by navArgs()
    private var dialogue by Delegates.notNull<DialogueModel>()

    @Inject
    lateinit var factory: ChatViewModelFactory.Factory
    private val chatViewModel: ChatViewModel by viewModels {
        factory.create()
    }

    private lateinit var name: TextView
    private lateinit var backButton: CardView
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var load: ProgressBar
    private lateinit var error: TextView

    private val chatAdapter = ChatAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogue = args.dialogue

        initUi(view)

        initRecycler()

        initData()
    }

    private fun initUi(view: View) {
        name = view.findViewById<TextView>(R.id.name)
        backButton = view.findViewById<CardView>(R.id.back_button)
        chatRecyclerView = view.findViewById<RecyclerView>(R.id.chatRecyclerView)
        load = view.findViewById<ProgressBar>(R.id.load)
        error = view.findViewById<TextView>(R.id.error)

        name.text = dialogue.name
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        error.setOnClickListener {
            chatAdapter.retry()
        }
    }

    private fun initRecycler() {
        chatRecyclerView.adapter = chatAdapter

        val linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            true,
        )
        chatRecyclerView.layoutManager = linearLayoutManager

        lifecycleScope.launch {
            chatAdapter.loadStateFlow.collectLatest { loadStates ->
                load.isVisible = loadStates.refresh is LoadState.Loading
                error.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            dialogue.id?.let {
                chatViewModel.getMessages(it).observe(viewLifecycleOwner) { pagingData ->
                    pagingData?.let { messagePagingData ->
                        chatAdapter.submitData(lifecycle, messagePagingData)
                    }
                }
            }
        }
    }
}
