package nl.ns.duckviewer.presentation.duck.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_duck.view.*
import nl.ns.duckviewer.R
import nl.ns.duckviewer.presentation.duck.DuckViewData

class DuckAdapter : RecyclerView.Adapter<DuckAdapter.DuckViewHolder>() {

    var ducks: List<DuckViewData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var duckClickListener: ((DuckViewData) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuckViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_duck, parent, false)
        return DuckViewHolder(v)
    }

    override fun getItemCount(): Int = ducks.size

    override fun onBindViewHolder(holder: DuckViewHolder, position: Int) = holder.bindDuck(ducks[position])

    inner class DuckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindDuck(duckData: DuckViewData) {
            with(itemView) {
                duckName.text = duckData.name
                duckSound.text = duckData.sound
                setOnClickListener { duckClickListener(duckData) }
            }
        }
    }
}