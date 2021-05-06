package com.domba.api_common

import com.domba.api_common.models.playground.Match
import com.domba.api_common.models.playground.MatchInfo
import com.domba.api_common.models.playground.PaginatedData
import com.domba.api_common.models.playground.PlayerMatchItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestInterface {

    //MATCH
    @GET("matchList/{date}/{timeOffset}")
    suspend fun getMatchesByDay(
        @Path("date") date: String,
        @Path("timeOffset") timeOffset: String
    ): MutableList<Match>

    @GET("matchList/live")
    suspend fun getLiveMatches(): Call<MutableList<Match>>

    @GET("match/{id}")
    suspend fun getMatchId(@Path("id") id: Long): Match

    @GET("match/{id}/info")
    suspend fun getMatchInfo(@Path("id") id: Long): MatchInfo
}