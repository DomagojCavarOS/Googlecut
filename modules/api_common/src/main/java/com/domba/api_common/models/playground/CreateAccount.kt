package com.domba.api_common.models.playground

class CreateAccount(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val email: String,
    val password: String,
    val redirectAfterVerification: String,
    val playApplicationId: String
)

class User(
    var active: Boolean,
    val id: Long,
    val code: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val email: String,
    val external: Boolean,
    val displayName: String
)