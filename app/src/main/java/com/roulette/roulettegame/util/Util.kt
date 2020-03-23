package com.roulette.roulettegame.util

import kotlin.random.Random

val RANDOM = Random(System.currentTimeMillis())

fun getRandomNumberInRange(min: Int, max: Int): Int {
    require(min < max) { "max must be greater than min" }
    return RANDOM.nextInt(max - min + 1) + min
}