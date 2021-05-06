package com.domba.api_common.models.playground

import com.domba.api_common.models.playground.Competition

data class PlayerStats(
    val minutesPlayed: Int?,
    val matchesPlayed: Int,
    val fullMatchesPlayed: Int,
    val goals: Int,
    val penalties: Int,
    val ownGoals: Int,
    val yellowCards: Int,
    val redCards: Int,
    val competition: Competition
)