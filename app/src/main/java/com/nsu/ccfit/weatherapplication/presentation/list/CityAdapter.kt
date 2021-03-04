package com.nsu.ccfit.weatherapplication.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.domain.City

class CityAdapter(private val onClick: (City) -> Unit) : RecyclerView.Adapter<CityHolder>() {

    var cities: List<City> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.count()
}

class CityHolder(itemView: View, private val onClick: (City) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val cityName: TextView = itemView.findViewById(R.id.cityText)
    private val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)

    fun bind(city: City) {
        cityName.text = city.name
        descriptionText.text = city.description ?: itemView.context.getString(R.string.description_absent)
        itemView.setOnClickListener { onClick(city) }
    }
}