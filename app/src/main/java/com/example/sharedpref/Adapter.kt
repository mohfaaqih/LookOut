package com.example.sharedpref

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Adapter(private val mList: List<DataViewModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cv, parent, false)



        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = mList[position]
        holder.setupView(dataModel.Username, dataModel.Description, dataModel.Created_at)


        var gusername :String = dataModel.Username
        var gdescription : String = dataModel.Description
        var gdID : String = dataModel.Id
        var gdSSID : String = dataModel.Session_id


        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, detail::class.java)
            intent.putExtra("iUsername", gusername)
                intent.putExtra("iDescription", gdescription)
            intent.putExtra("ID", gdID)
            intent.putExtra("iSSID", gdSSID)
            v.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var username : TextView
        var description : TextView
        var created_at : TextView




        init {
            username = view.findViewById(R.id.txt_title)
            description = view.findViewById(R.id.txt_description)
            created_at = view.findViewById(R.id.txt_time)
        }



        fun setupView(titleData: String, descriptionData: String, timeData: String) {
            username.text = titleData
            description.text = descriptionData
            created_at.text = timeData


//            itemView.setOnClickListener() {
//                Toast.makeText(it.context, "Kamu memilih ${titleData}", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}




