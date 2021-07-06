package com.noisegain.gitrepos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.gitrepos.databinding.RepositoryRowBinding

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.Holder>() {

    private var reposList = ArrayList<Repository>()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = RepositoryRowBinding.bind(item)
        fun bind(repository: Repository) = with(binding) {
            name.text = repository.name
            desc.text = repository.description
            lang.text = repository.lang
            user.text = repository.userName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_row, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(reposList[position])
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    fun refresh(list: ArrayList<Repository>) {
        reposList = list
        notifyDataSetChanged()
    }

    fun addRep(repository: Repository) {
        reposList.add(repository)
        notifyDataSetChanged()
    }
}