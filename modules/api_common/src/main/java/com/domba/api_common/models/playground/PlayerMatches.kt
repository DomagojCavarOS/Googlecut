package com.domba.api_common.models.playground

data class PlayerMatches(val result: MutableList<PlayerMatchItem>)
data class PlayerMatchItem(
    val id: Long,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamResult: TeamResult?,
    val awayTeamResult: TeamResult?,
    val dateTimeUTC: Long,
    var competition: Competition?,
    val result: String?,
    val team: String,
    val liveStatus: String,
    val playerMatchStats: PlayerMatchStats?,
    val round: String,
    val homeTeamRedCards: Int,
    val awayTeamRedCards: Int,
    var payload: Competition?
)

data class PlayerMatchStats(
    val minutesPlayed: Int,
    val goals: Int,
    val penalties: Int,
    val singleYellow: Boolean,
    val red: Boolean,
    val secondYellow: Boolean
)