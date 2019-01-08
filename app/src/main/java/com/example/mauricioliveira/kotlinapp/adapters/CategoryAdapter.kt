package com.example.mauricioliveira.kotlinapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mauricioliveira.kotlinapp.R
import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory
import com.example.mauricioliveira.kotlinapp.privates.mainmenu.MainMenuViewInterface
import kotlinx.android.synthetic.main.category.view.*

class CategoryAdapter (private val items : ArrayList<TriviaCategory>, private var listener: MainMenuViewInterface) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        parent.categoryButton.text = items[position].name

        parent.categoryButton.setOnClickListener { listener.onSelectCategory(items[position])}
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val categoryButton = view.button!!
    }
}