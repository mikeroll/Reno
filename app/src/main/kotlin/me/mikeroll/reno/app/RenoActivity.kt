package me.mikeroll.reno.app

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.id

public class RenoActivity : Activity() {

    var listFragment: EventListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        frameLayout { id = R.id.container }
        loadListFragment(restore = savedInstanceState == null)
    }

    private fun loadListFragment(restore: Boolean) {
        val fm = getFragmentManager()
        val tag = "eventlist"

        if (restore) {
            listFragment = listFragment ?: EventListFragment.newInstance()
            fm.beginTransaction()
              .replace(R.id.container, listFragment!!, tag)
              .commit()
        } else {
            listFragment = fm.findFragmentByTag(tag) as EventListFragment
        }
    }
}
