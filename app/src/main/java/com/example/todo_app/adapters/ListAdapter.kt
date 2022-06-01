package com.example.todo_app.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.R
import com.example.todo_app.databinding.ListBinding
import com.example.todo_app.room.ListEntity

class ListAdapter (var list: List<ListEntity>,var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ListAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: ListBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: ListEntity,position: Int) {

            malumotItemBinding.listName.text = malumotlar.title
            malumotItemBinding.listName.setTextColor(Color.parseColor(malumotlar.textcolor))
            malumotItemBinding.total.setTextColor(Color.parseColor(malumotlar.textcolor))
            malumotItemBinding.cardview.setBackgroundColor(Color.parseColor(malumotlar.color))



            malumotItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: ListEntity)
    }
}