package com.noisegain.gitrepos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.gitrepos.databinding.UserViewBinding
import kotlinx.android.synthetic.main.repository_row.view.*


interface OnUserClickListener {
    fun onUserItemClicked(position: Int)
    fun writePref()
}

class UsersAdapter(private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UsersAdapter.Holder>() {

    private var usersList = mutableListOf<User>()

    class Holder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = UserViewBinding.bind(item)

        fun bind(user: User): UserViewBinding = with(binding) {
            nameView.text = user.name
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder) {
        val user = usersList[position]
        itemView.setOnClickListener {
            onUserClickListener.onUserItemClicked(position)
        }
        var state = user.name in favorites
        val back = arrayOf(R.drawable.baseline_bookmark_border_black_36, R.drawable.baseline_bookmark_black_36)
        with(bind(user)) {
            addFav.setImageResource(back[if (state) 1 else 0])
            addFav.setOnClickListener {
                state = !state
                addFav.setImageResource(back[if (state) 1 else 0])
                if (state) favorites.add(user.name)
                else favorites.remove(user.name)
                onUserClickListener.writePref()
                println(user.name)
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun refresh(list: MutableList<User>) {
        usersList = list
        notifyDataSetChanged()
    }

    fun addRep(user: User) {
        usersList.add(user)
        notifyDataSetChanged()
    }

}