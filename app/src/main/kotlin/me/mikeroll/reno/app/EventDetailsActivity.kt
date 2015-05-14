package me.mikeroll.reno.app

import android.app.Activity
import android.os.Bundle
import com.google.gson.Gson
import me.mikeroll.reno.client.Event
import org.jetbrains.anko.*
import kotlin.properties.Delegates

public class EventDetailsActivity : Activity() {

    private val event: Event? by Delegates.lazy {
        val data = intent.getStringExtra("event")
        if (data != null) {
            Gson().fromJson(data, javaClass<Event>())
        } else {
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (event != null) {
            linearLayout {
                button(event!!.artists[0].name)
            }
        } else {
            longToast("Failed loading event :(")
            finish()
        }
    }
}
