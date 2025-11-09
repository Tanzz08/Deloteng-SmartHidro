package com.example.delotengsmarthidro.data.list.disease

data class Disease(
    val modelKey: String,
    val displayName: String,
    val characteristics: List<String>,
    val solution: List<String>
)
