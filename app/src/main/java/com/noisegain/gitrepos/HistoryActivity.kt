package com.noisegain.gitrepos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.gitrepos.databinding.HistoryViewBinding
import com.noisegain.gitrepos.databinding.UserViewBinding
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), OnUserClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        title = "History"
        val adapter = HistoryAdapter(this)
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = adapter
        adapter.refresh(history)
        clearButton.setOnClickListener {
            history.clear()
            adapter.refresh(history)
        }
    }

    class HistoryAdapter(private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<HistoryAdapter.Holder>() {

        private var usersList = mutableListOf<String>()

        class Holder(item: View): RecyclerView.ViewHolder(item) {

            private val binding = HistoryViewBinding.bind(item)

            fun bind(user: String) = with(binding) {
                nameView.text = user
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.history_view, parent,false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) = with(holder) {
            itemView.setOnClickListener {
                onUserClickListener.onUserItemClicked(position)
            }
            bind(usersList[position])
        }

        override fun getItemCount(): Int {
            return usersList.size
        }

        fun refresh(list: ArrayList<String>) {
            usersList = list
            notifyDataSetChanged()
        }
    }

    override fun onUserItemClicked(position: Int) {
        historyQ = history[position]
        onBackPressed()
    }

    override fun writePref() {
        return
    }
}