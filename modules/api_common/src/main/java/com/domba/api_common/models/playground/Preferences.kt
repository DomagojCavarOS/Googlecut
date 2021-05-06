package com.domba.api_common.models.playground

data class Preferences(
    val ads: Boolean,
    val twitter: String,
    val locale: String,
    val maxFavoriteMatch: Int,
    val maxFavoriteTeam: Int,
    val maxFavoriteCompetition: Int,
    val maxFavoritePlayer: Int,
    val authentication: Boolean,
    val legal: MutableList<LegalData>?,
    val mapOverlays: MutableList<Overlay>?
)

data class LegalData(val link: String, val title: String)
data class Overlay(val label: String, val value: String)
data class SelectedValue(val mapOverlay: String)