package me.mikeroll.reno.app

import android.app.Activity
import android.os.Bundle
import android.util.Log

import me.mikeroll.reno.client.reno
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers

public class RenoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reno_activity)
        var events = reno.findEvents("Minsk")
        events.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ j ->
                    Log.d("EVENT", j[0].venue.country)
                })
    }
}
