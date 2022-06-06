package com.coditas.ism.demoplayground

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coditas.ism.demoplayground.databinding.SingleTodoItemBinding


class EventListAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {

    class MyViewHolder(binding:SingleTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val eventWithDate = binding.txtNoteTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = SingleTodoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCategoryPosition = eventList[position]
        holder.eventWithDate.text = currentCategoryPosition.date +" Event: "+ currentCategoryPosition.title
        Log.d("TAG Calender","${currentCategoryPosition.date}")
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}
