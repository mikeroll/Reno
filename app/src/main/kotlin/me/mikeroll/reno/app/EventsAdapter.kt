package me.mikeroll.reno.app

import android.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import me.mikeroll.reno.client.Event
import org.jetbrains.anko.*
import java.util.ArrayList

public class EventsAdapter(val fragment: Fragment) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    val events = ArrayList<Event>()

    override fun getItemCount() = events.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val ctx = parent!!.getContext()
        val v = ctx.layoutInflater.inflate(R.layout.event_view, parent, false)
        val main_artist = v.find<TextView>(R.id.main_artist)
        return ViewHolder(v, main_artist, main_artist)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder == null) return
        val item = events.get(position)
        holder.view.setOnClickListener {
            val act = fragment.getActivity()
            act?.startActivity<EventDetailsActivity>("event" to Gson().toJson(item))
        }
        holder.main_artist.text = item.artists[0].name
    }

    inner class ViewHolder(val view: View,
                           val main_artist: TextView,
                           val artists: TextView)
        : RecyclerView.ViewHolder(view)
}
