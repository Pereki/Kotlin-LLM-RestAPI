package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class OutputFormat(val message: String, val context: ArrayList<String>) {
}