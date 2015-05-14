package me.mikeroll.reno.app

import android.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import me.mikeroll.reno.client.Event
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.text
import java.text.SimpleDateFormat
import java.util.ArrayList

public class EventsAdapter(val fragment: Fragment) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    val events = ArrayList<Event>()

    override fun getItemCount() = events.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val ctx = parent!!.getContext()
        val v = ctx.layoutInflater.inflate(R.layout.event_cardview, parent, false)
        v.find<TextView>(R.id.right_text_button).text = "DETAILS"
        val main_artist = v.find<TextView>(R.id.titleTextView)
        val details = v.find<TextView>(R.id.descriptionTextView)
        return ViewHolder(v, main_artist, details)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder == null) return
        val item = events.get(position)
        holder.view.setOnClickListener {
            val act = fragment.getActivity()
            act?.startActivity<EventDetailsActivity>("event" to Gson().toJson(item))
        }
        holder.main_artist.text = item.artists[0].name
        val all_artists = item.artists.map { it.name }.join(", ")
        val date = SimpleDateFormat("EEEE, MMM dd, yyyy").format(item.datetime)
        holder.details.text = "${all_artists}\n\n${date}"
    }

    inner class ViewHolder(val view: View,
                           val main_artist: TextView,
                           val details: TextView)
        : RecyclerView.ViewHolder(view)
}
