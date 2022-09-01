package com.herbert.travelapp.api.extensions

import java.text.Normalizer

fun CharSequence.unaccent(): String {
    val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "").replace("ł", "l").replace("ę", "e").replace("ñ", "n").replace("ç", "c").replace("(", "-").replace(")","-")
}

fun CharSequence.toSearchableName(): String{
    return this.unaccent().lowercase().replace(" ", "-")
}