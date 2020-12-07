package com.example.citylist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citylist.R
import com.example.core.model.CityWeather
import kotlinx.android.synthetic.main.city_item.view.*

class CityListAdapter(private val clickListener: CityItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {
    private var items: List<CityWeather> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)
        )
    }

    fun update(newItems: List<CityWeather>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.rootView.apply {
            cityName.text = item.name
            country.text = item.country
            temperature.text = item.temperature.toString()
            weatherDescription.text = item.weatherDescription
            setOnClickListener {
                clickListener.onItemClick(item)
            }
            deleteButton.setOnClickListener {
                clickListener.onRemoveClick(item)
            }
        }
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface CityItemClickListener {
        fun onItemClick(city: CityWeather)
        fun onRemoveClick(city: CityWeather)
    }
}