package me.mikeroll.reno.client

import com.google.gson.GsonBuilder
import me.mikeroll.reno.app.BuildConfig
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.converter.GsonConverter
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

public trait BandsintownService {
    [GET("/events/search")]
    fun findEvents(
            [Query("location")] location: String,
            [Query("per_page")] perPage: Int = 20
    ) : Observable<Array<Event>>
}

private val endpoint = "http://api.bandsintown.com/"
private val interceptor = RequestInterceptor { request ->
    request.addQueryParam("app_id", "me.mikeroll.reno")
    request.addQueryParam("format", "json")
}

private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

public val reno: BandsintownService = RestAdapter.Builder()
        .setEndpoint(endpoint)
        .setRequestInterceptor(interceptor)
        .setConverter(GsonConverter(gson))
        .build()
        .create(javaClass<BandsintownService>())
