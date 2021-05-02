package com.example.calculoimc

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.graph_item.view.*

class GraphImcListAdapter(private val graphList: List<GraphImc>,
                            private val context: Context) : RecyclerView.Adapter<GraphImcListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(graph: GraphImc) {
            val tittle = itemView.txt_tittle
            val description = itemView.txt_description
            val color = itemView.txt_color

            tittle.text = graph.title
            description.text = graph.description
            color.setBackgroundColor(Color.parseColor(graph.color))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.graph_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val graph = graphList[position]
        holder.bind(graph)
    }

    override fun getItemCount(): Int = graphList.size
}