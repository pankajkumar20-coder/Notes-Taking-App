package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val listner: NoteClicked): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.deleteButton.setOnClickListener {
            listner.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    override fun getItemCount() = allNotes.size
}

interface NoteClicked {
    fun onItemClicked(note: Note)
}