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
}

class UsersAdapter(private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UsersAdapter.Holder>() {

    private var usersList = mutableListOf<User>()

    class Holder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = UserViewBinding.bind(item)

        fun bind(user: User) = with(binding) {
            nameView.text = user.name
            var state = user.name in favorites
            val back = arrayOf(R.drawable.baseline_bookmark_border_black_36, R.drawable.baseline_bookmark_black_36)
            addFav.setImageResource(back[if (state) 1 else 0])
            addFav.setOnClickListener {
                state = !state
                addFav.setImageResource(back[if (state) 1 else 0])
                if (state) favorites.add(user.name)
                else favorites.remove(user.name)
                prefConfig.writePref(favorites)
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

    fun refresh(list: MutableList<User>) {
        usersList = list
        notifyDataSetChanged()
    }

    fun addRep(user: User) {
        usersList.add(user)
        notifyDataSetChanged()
    }

}