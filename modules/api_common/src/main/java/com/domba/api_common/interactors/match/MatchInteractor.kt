package com.domba.api_common.interactors.match

import com.domba.api_common.RestInterface
import com.domba.api_common.models.playground.Match
import com.domba.api_common.models.playground.MatchInfo

class MatchInteractorImpl(val restInterface: RestInterface) :
    MatchInteractor {

    override suspend fun getMatchesMaps(date: String, timeOffset: String) : MutableList<Match> {
        return  restInterface.getMatchesByDay(date, timeOffset)
    }

    override suspend fun getMatchById(id: Long): Match {
        return restInterface.getMatchId(id)
    }

    override suspend fun getMatchInfo(id: Long): MatchInfo {
        return restInterface.getMatchInfo(id)
    }
}

interface MatchInteractor {
    suspend fun getMatchesMaps(date: String, timeOffset: String): MutableList<Match>
    suspend fun getMatchById(id: Long): Match
    suspend fun getMatchInfo(id: Long): MatchInfo
}