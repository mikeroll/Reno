package me.mikeroll.reno.app

import android.app.Fragment
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.mikeroll.reno.client.Event
import me.mikeroll.reno.client.reno
import org.jetbrains.anko.ctx
import org.jetbrains.anko.id
import org.jetbrains.anko.scrollBarStyle
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates

public class EventListFragment : Fragment() {

    companion object {
        fun newInstance() = EventListFragment()
    }

    val adapter: EventsAdapter
            by Delegates.lazy { EventsAdapter(this) }
    val locationProvider: ReactiveLocationProvider
            by Delegates.lazy { ReactiveLocationProvider(ctx) }

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

    private fun loadEvents(location: String = "") {
        val fetchEvents = { loc: String ->
            reno.findEvents(loc)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { events -> updateList(events) }
        }
        if (location != "") {
            fetchEvents(location)
        } else {
            locationProvider.getLastKnownLocation()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { l ->
                    val loc = Location.convert(l.getLatitude(), Location.FORMAT_DEGREES) + "," +
                            Location.convert(l.getLongitude(), Location.FORMAT_DEGREES)
                    fetchEvents(loc)
                }
        }
    }

    private fun updateList(result: Array<Event>) {
        adapter.events.addAll(result)
        adapter.notifyDataSetChanged()
    }
}
