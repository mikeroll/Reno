package me.mikeroll.reno.app

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.mikeroll.reno.client.Event
import org.jetbrains.anko.*
import java.util.ArrayList

public class EventsAdapter : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    val events = ArrayList<Event>()

    override fun getItemCount() = events.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val ctx = parent!!.getContext()
        val v = ctx.layoutInflater.inflate(R.layout.event_view, parent, false)
        val artist = v.find<TextView>(R.id.artist)
        return ViewHolder(v, artist, artist)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder == null) return

        val item = events.get(position)
        holder.main_artist.text = item.artists[0].name
    }

    inner class ViewHolder(val view: View,
                           val main_artist: TextView,
                           val artists: TextView)
        : RecyclerView.ViewHolder(view)
}
