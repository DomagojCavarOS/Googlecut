package com.domba.api_common.models.playground

data class NotificationSettings(
    var matchNotifications: Boolean,
    var teamNotifications: Boolean,
    var competitionNotifications: Boolean,
    var playerNotifications: Boolean
)