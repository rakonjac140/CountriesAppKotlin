package com.appcrafters.countries.base.model

data class Country(
    val name: String = "",
    val capital: String = "",
    val subregion: String = "",
    val region: String = "",
    val population: Long = 0,
    val latlng: Array<Double> = arrayOf(),
    val area: Float = 0.0F,
    val flags: Flags = Flags(),
    val independent: Boolean = false,
    val languages: Array<Language> = arrayOf(),
)

data class Language(
    val iso639_1: String = "",
    val iso639_2: String = "",
    val name: String = "",
    val nativeName: String = "",
)

data class Flags(
    val svg: String = "",
    val png: String = "",
)