package com.domba.api_common.models.playground

import com.domba.api_common.models.playground.MatchPhase

data class NotificationsData(val deviceId: String, val notificationToken: String)

data class RemoteMessageData(
    val eventTypeFCD: String,  //OK
    val minute: String,  //OK
    val matchMinute: String?,  //OK
    val stoppageTime: String?,
    val shirtNumber: Int?,
    val shortName: String,  //OK
    val shirtNumber2: Int?,
    val shortName2: String?,  //OK
    val teamName: String,   //OK
    val team: String,  //OK
    val matchId: Long,  //OK
    val matchName: String,  //OK
    val matchResult: String,  //OK
    val homeTeamPicture: String,  //OK
    val awayTeamPicture: String,  //OK
    val round: String?,
    val competitionName: String?,
    val matchPhase: MatchPhase?,
    val displayMinute: String?,
    val eventTypeName: String
)