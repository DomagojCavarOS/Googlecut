package com.domba.api_common.models.playground

data class Form(val id: Long, val code: String, val name: String, val formFieldConfiguration: MutableList<Field>, val displayName: String)
data class Field(val id: Long, val code: String, val name: String, val description: String, val visible: Boolean, val required: Boolean, val displayName: String)