package com.example.anteeoneapp.ui.objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    private var list: SortedList<Todo>

    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val todoTitle: TextView = itemView.findViewById(R.id.text_todo_title)
        private val todoDeleteButton: ImageView = itemView.findViewById(R.id.button_todo_done)

        private lateinit var mTodo: Todo

        init {
            todoDeleteButton.setOnClickListener{
                App.getInstance().todoDao.delete(mTodo)
            }
        }

        fun bind(todo: Todo){
            mTodo = todo
            todoTitle.setText(mTodo.title)
            todoDeleteButton.setOnClickListener{
                App.getInstance().todoDao.delete(todo)
            }
        }

    }

    init {
        list = SortedList(Todo::class.java,object: SortedListAdapterCallback<Todo>(this){
            override fun areItemsTheSame(item1: Todo, item2: Todo): Boolean {
                return item1.id == item2.id
            }

            override fun compare(o1: Todo, o2: Todo): Int {
                return (o1.timeStamp - o2.timeStamp).toInt()
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position,count)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_todo,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return list.size()
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setItems(newList: List<Todo>){
        list.replaceAll(newList)
    }

}