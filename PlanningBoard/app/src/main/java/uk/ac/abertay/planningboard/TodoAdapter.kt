package uk.ac.abertay.planningboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class TodoAdapter(private val context : Context, val listener: TodoClickListener) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val TodoList = ArrayList<Todo>()
    //Will contain all elements from within the database
    private val fullList =ArrayList<Todo>()


    override fun getItemCount(): Int {
        return TodoList.size
    }

    fun updateList(newList: List<Todo>){

        fullList.clear()
        fullList.addAll(newList)

        TodoList.clear()
        TodoList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){

        TodoList.clear()

        for(item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase()) == true || item.todo?.lowercase()?.contains(search.lowercase()) == true){

                TodoList.add(item)
            }

        }

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.todo_item,parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = TodoList[position]
        holder.title.text = currentTodo.title
        holder.title.isSelected = true
        holder.TodoText.text = currentTodo.todo

        holder.todo_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.todo_layout.setOnClickListener{

            listener.onItemClicked(TodoList[holder.adapterPosition])

        }

        holder.todo_layout.setOnLongClickListener {

            listener.onLongItemClicked(TodoList[holder.adapterPosition], holder.todo_layout)
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

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val todo_layout = itemView.findViewById<CardView>(R.id.CardLayout)
        val title = itemView.findViewById<TextView>(R.id.TodoTitle)
        val TodoText = itemView.findViewById<TextView>(R.id.TodoText)
    }

    interface TodoClickListener{
        fun onItemClicked(todo:Todo)
        fun onLongItemClicked(todo:Todo,cardView: CardView)
    }

}