package com.noisegain.gitrepos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.gitrepos.databinding.UserViewBinding
import kotlinx.android.synthetic.main.repository_row.view.*


interface OnUserClickListener {
    fun onUserItemClicked(position: Int)
}

class UsersAdapter(private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UsersAdapter.Holder>() {

    private var usersList = ArrayList<User>()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = UserViewBinding.bind(item)
        fun bind(user: User) = with(binding) {
            nameView.text = user.name
            addFav.setOnClickListener {
                println("Hahahahah")
                println(user.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(usersList[position])
        holder.itemView.setOnClickListener {
            onUserClickListener.onUserItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun refresh(list: ArrayList<User>) {
        usersList = list
        notifyDataSetChanged()
    }

    fun addRep(user: User) {
        usersList.add(user)
        notifyDataSetChanged()
    }

}