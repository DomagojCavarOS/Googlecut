package com.domba.api_common.models.playground

const val WIN = "W"
const val DRAW = "D"
const val LOSSES = "L"

data class LeagueTeam(
    val team: Team,
    val played: Int,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val points: Int,
    val negativePoints: Int,
    val position: Int,
    val m1: LastMatches,
    val m2: LastMatches,
    val m3: LastMatches,
    val m4: LastMatches,
    val m5: LastMatches
)

data class LastMatches(val matchId: String, val result: String?)