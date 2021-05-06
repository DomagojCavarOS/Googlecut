package com.domba.api_common.models.playground

import com.domba.api_common.models.playground.Player
import com.domba.api_common.models.playground.Team

data class Stats(val player: Player, val team: Team, val value: Int?)