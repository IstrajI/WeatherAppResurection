package com.example.citylist.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.citylist.R
import com.example.core.model.CityWeather
import kotlinx.android.synthetic.main.suggestion_view.view.*

class CitySuggestionsAdapter(context: Context, itemViewId: Int) : ArrayAdapter<CityWeather>(context, itemViewId) {
    var data: List<CityWeather> = listOf()
    override fun getCount() = data.size
    override fun getItem(position: Int) = data[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestion_view, parent, false)
        val item = getItem(position)

        view.rootView.cityName.text = item.name
        view.rootView.country.text = item.country
        return view
    }


    fun updateData(newItems: List<CityWeather>) {
        data = newItems
        notifyDataSetChanged()
    }
}