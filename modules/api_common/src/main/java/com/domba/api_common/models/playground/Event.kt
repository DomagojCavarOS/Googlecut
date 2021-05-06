package com.domba.api_common.models.playground

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val GOALKEEPER = "G"
const val EVENT_GOAL = "GOAL"
const val EVENT_OWN_GOAL = "OWN_GOAL"
const val EVENT_PENALTY = "PENALTY"
const val EVENT_PENALTY_SCORE = "PENALTY_GOAL"
const val EVENT_PENALTY_MISS = "PENALTY_FAILED"
const val EVENT_YELLOW = "YELLOW"
const val EVENT_SECCOND_YELLOW = "SECOND_YELLOW"
const val EVENT_RED = "RED"
const val EVENT_EXPULSION = "EXPULSION"
const val EVENT_SUBSTITUTION = "SUBSTITUTION"
const val EVENT_START = "START"
const val EVENT_END = "END"
const val EVENT_FULL_TIME = "FULL_TIME"
const val EVENT_SIX_METERS = "SIX_METERS"
const val EVENT_TEN_METERS = "TEN_METERS"
const val EVENT_ACCUMULATED_FOUL = "ACCUMULATED_FOUL"
const val EVENT_TIME_OUT = "TIME_OUT"

@Parcelize
data class Event(
    val eventId: Long,
    val eventType: EventType,
    val matchPhase: MatchPhase,
    val minuteFull: Int?,
    val displayMinute: String?,
    val player: Player?,
    val player2: Player?,
    val teamOfficial: Player?,
    val club: Team?,
    val homeTeam: Boolean?,
    val competition: Competition?
) : Parcelable

@Parcelize
data class EventType(val eventTypeId: Int?, val name: String?, val fcdName: String?) : Parcelable

@Parcelize
data class MatchPhase(val phaseTypeId: Long, val fcdName: String?, val name: String?) : Parcelable

@Parcelize
data class Player(
    val personId: Long?,
    val roleId: Long,
    val fifaId: String?,
    val name: String,
    val shortName: String?,
    val starting: Boolean,
    val captain: Boolean?,
    val position: String?,
    val picture: String?,
    val nationality: String?,
    val gender: String?,
    val age: Int?,
    val shirtNumber: Int?,
    val role: String?,
    var club: Team?,
    val events: List<Event>?,
    val flag: String?,
    val hideProfile: Boolean = false,
    var payloadCompetition: Competition?,
    var payloadMatch: Match?
) : Parcelable