package com.domba.api_common.models.playground

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val STATUS_RUNNING = "RUNNING"
const val STATUS_PLAYED = "PLAYED"
const val STATUS_SCHEDULED = "SCHEDULED"
const val STATUS_CANCELED = "CANCELED"
const val STATUS_POSTPONED = "POSTPONED"

@Parcelize
data class Match(
    var id: Long,
    var round: String?,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamResult: TeamResult?,
    val awayTeamResult: TeamResult?,
    val currentMinute: String?,
    val currentMatchPhase: MatchPhase?,
    val liveStatus: String?,
    val dateTimeUTC: Long,
    val competition: Competition,
    val attendance: Int?,
    val facility: Facility?,
    val showEvents: Boolean,
    val homeTeamRedCards: Int,
    val awayTeamRedCards: Int
) : Parcelable

@Parcelize
data class Team(
    val id: Long,
    val name: String?,
    val picture: String?,
    val showStandings: Boolean?,
    val parent: Parent?,
    val place: String?,
    val fifaId: String?,
    val country: String?,
    val website: String?,
    val email: String?,
    val phone: String?,
    val mobilePhone: String?,
    val instagram: String?,
    val facebook: String?,
    val twitter: String?,
    val address: String?,
    val facility: Facility?,
    val facilityAddress: String?,
    val latitude: Double?,
    val longitude: Double?,
    val facilityLongitude: Double?,
    val facilityLatitude: Double?,
    val hasTeams: Boolean?,
    var payloadCompetition: Competition?
) : Parcelable

@Parcelize
data class TeamResult(val current: Int, val regular: Int, val half: Int) : Parcelable

@Parcelize
data class Competition(
    val id: Long,
    val name: String?,
    val parentId: Long?,
    val parentName: String?,
    val picture: String?,
    val showStandings: Boolean,
    val showStats: Boolean,
    var competitionElements: List<Competition>?

) : Parcelable

@Parcelize
data class MatchInfo(
    val homeKit: String?,
    val awayKit: String?,
    val homeGKKit: String?,
    val awayGKKit: String?,
    val refereeKit: String?,
    val matchOfficials: List<Player>
) : Parcelable

data class TeamAnalytics(val team: Team?, val competition: Competition?, val match: Match?)