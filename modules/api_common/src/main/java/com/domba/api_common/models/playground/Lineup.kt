package com.domba.api_common.models.playground

data class Lineup(val home: TeamLinap, val away: TeamLinap)

data class TeamLinap(val players: MutableList<Player>, val officials: MutableList<Player>)