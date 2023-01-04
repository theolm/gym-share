package com.theolm.gym_share.extensions

private const val alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ"

fun Int.toAlphabetLetter() : Char {
    return if (this <= alphabet.length) {
        alphabet[this]
    } else {
        '?'
    }
}