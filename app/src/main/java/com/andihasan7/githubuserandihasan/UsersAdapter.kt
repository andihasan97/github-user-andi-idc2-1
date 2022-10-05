package com.andihasan7.githubuserandihasan

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andihasan7.githubuserandihasan.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class UsersAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val u = listUser[position]
        Glide.with(holder.itemView.context)
            .load(u.avatar)
            .circleCrop()
            .into(holder.binding.avatarU)
        holder.binding.tvName.text = u.name
        holder.binding.tvUsername.text = u.username
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listUser[holder.adapterPosition])
        }

        holder.binding.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.FIELD, u)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: User)
    }

}