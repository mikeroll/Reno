package me.mikeroll.reno.client

import java.util.Date

class Event(val id: Int,
            val datetime: Date,
            val artists: Array<Artist>,
            val venue: Venue)

class Artist(val name: String,
             val mbid: String?)

class Venue(val id: Int,
            val name: String,
            val city: String,
            val region: String,
            val country: String,
            val latitude: Double,
            val longitude: Double)
