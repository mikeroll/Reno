package me.mikeroll.reno.app

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*

public class EventListFragment : Fragment() {

    companion object {
        fun newInstance() = EventListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return with(ctx) {
            recyclerView {
                id = R.id.eventlist
                scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                setHasFixedSize(true)
            }
        }
    }
}
