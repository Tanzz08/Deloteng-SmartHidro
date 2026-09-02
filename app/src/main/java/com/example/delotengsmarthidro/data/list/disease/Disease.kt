package com.example.delotengsmarthidro.data.list.disease

data class Disease(
    val modelKey: String,
    val displayName: String,
    val severity: String,
    val severityExplanation: String,
    val causes: List<String>,
    val characteristics: List<String>,
    val solution: List<String>
)
