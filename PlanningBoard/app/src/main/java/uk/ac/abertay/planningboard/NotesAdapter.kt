package uk.ac.abertay.planningboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class NotesAdapter(private val context : Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    //Will contain all elements from within the database
    private val fullList =ArrayList<Note>()


    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList: List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){

        NotesList.clear()

        for(item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase()) == true || item.note?.lowercase()?.contains(search.lowercase()) == true){

                NotesList.add(item)
            }

        }

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.note_item,parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.noteText.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener{

            listener.onItemClicked(NotesList[holder.adapterPosition])

        }

        holder.notes_layout.setOnLongClickListener {

            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true

        }

    }

    fun randomColor() : Int{

        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)
        list.add(R.color.NoteColor7)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]



    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notes_layout = itemView.findViewById<CardView>(R.id.CardLayout)
        val title = itemView.findViewById<TextView>(R.id.noteTitle)
        val noteText = itemView.findViewById<TextView>(R.id.noteText)
        val date = itemView.findViewById<TextView>(R.id.noteDate)
    }

    interface NotesClickListener{
        fun onItemClicked(note:Note)
        fun onLongItemClicked(note:Note,cardView: CardView)
    }

}