package me.mikeroll.reno.app

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.mikeroll.reno.client.Event
import me.mikeroll.reno.client.reno
import org.jetbrains.anko.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList
import kotlin.properties.Delegates

public class EventListFragment : Fragment() {

    companion object {
        fun newInstance() = EventListFragment()
    }

    val adapter: EventsAdapter by Delegates.lazy { EventsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return with(ctx) {
            recyclerView {
                id = R.id.eventlist
                scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                setHasFixedSize(true)
                setLayoutManager(LinearLayoutManager(ctx))
                setAdapter(adapter)
            }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            loadEvents()
        }
    }

    private fun loadEvents(location: String = "Minsk") {
        val o = reno.findEvents(location)
        o.subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe { events -> updateList(events) }
    }

    private fun updateList(result: Array<Event>) {
        adapter.events.addAll(result)
        adapter.notifyDataSetChanged()
    }
}
