package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ImportFormat(val message: String, val context: ArrayList<String>? = kotlin.collections.ArrayList()) {
}