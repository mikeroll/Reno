package me.mikeroll.reno.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.event_view.*
import me.mikeroll.reno.client.Event
import org.jetbrains.anko.intent
import org.jetbrains.anko.longToast
import org.jetbrains.anko.onClick
import org.jetbrains.anko.text
import java.text.SimpleDateFormat

public class EventDetailsActivity : Activity() {

    private fun getEvent(): Event? {
        val data = intent.getStringExtra("event")
        return if (data != null) {
            Gson().fromJson(data, javaClass<Event>())
        } else {
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val event = getEvent()
        getActionBar().setTitle("Events near you")
        if (event != null) {
            setContentView(R.layout.event_view)
            titleTextView.text = event.artists[0].name
            descriptionTextView.text = event.artists.map { it.name }.join(", ")
            venue_info.text = "${event.venue.name}\n\n${event.venue.city}, ${event.venue.country}"
            val e_date = SimpleDateFormat("EEEE, MMM dd, yyyy").format(event.datetime)
            date.text = e_date
            right_text_button.onClick {
                val intent = Intent(Intent.ACTION_INSERT)
                with(intent) {
                    setType("vnd.android.cursor.item/event")
                    putExtra("beginTime", event.datetime.getTime())
                    putExtra("allDay", true)
                    putExtra("title", event.artists[0].name)
                }
                startActivity(intent)
            }

        } else {
            longToast("Failed loading event :(")
            finish()
        }
    }
}
