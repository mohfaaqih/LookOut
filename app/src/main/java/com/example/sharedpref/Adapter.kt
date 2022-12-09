package com.example.sharedpref


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter (var ctx: Context, var resource: Int, var item: ArrayList<Model>): ArrayAdapter<Model>(ctx, resource, item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resource, null)

        val title = view.findViewById<TextView>(R.id.txt_title)
        val description = view.findViewById<TextView>(R.id.txt_description)
        val img = view.findViewById<ImageView>(R.id.icon_image)

        title.text = item[position].Title
        description.text = item[position].Description

        return view
    }
}