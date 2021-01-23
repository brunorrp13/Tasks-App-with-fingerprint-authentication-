package com.example.tasks.view.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    private var mList: List<TaskModel> = arrayListOf()
    private lateinit var mListener: TaskListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_task_list, parent, false)
        return TaskViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: TaskListener) {
        mListener = listener
    }

    fun updateList(list: List<TaskModel>) {
        mList = list
        notifyDataSetChanged()
    }

}

class TaskViewHolder(itemView: View, val listener: TaskListener) :
    RecyclerView.ViewHolder(itemView) {

    private val mDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description)
    private var mTextPriority: TextView = itemView.findViewById(R.id.text_priority)
    private var mTextDueDate: TextView = itemView.findViewById(R.id.text_due_date)
    private var mImageTask: ImageView = itemView.findViewById(R.id.image_task)

    fun bindData(task: TaskModel) {

        this.mTextDescription.text = task.description
        val priorities = arrayOf("Low", "Medium", "High", "Critical")
        this.mTextPriority.text = priorities.elementAt(task.priorityId - 1)

        val date = SimpleDateFormat("yyyy-MM-dd").parse(task.dueDate)
        this.mTextDueDate.text = mDateFormat.format(date)

        // Faz o tratamento para tarefas já completas
        if (task.complete) {
            mTextDescription.setTextColor(Color.GRAY);
            mImageTask.setImageResource(R.drawable.ic_done);
        } else {
            mTextDescription.setTextColor(Color.BLACK);
            mImageTask.setImageResource(R.drawable.ic_todo);
        }

        // Events
        mTextDescription.setOnClickListener { listener.onListClick(task.id) }
        mImageTask.setOnClickListener {
            if (task.complete) {
                listener.onUndoClick(task.id)
            } else {
                listener.onCompleteClick(task.id)
            }
        }

        itemView.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.task_removal)
                .setMessage(R.string.remove_task)
                .setPositiveButton(R.string.yes) { dialog, which ->
                    listener.onDeleteClick(task.id)
                }
                .setNeutralButton(R.string.cancel, null)
                .show()
            true
        }

    }

}