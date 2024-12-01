package com.app.android_test.core.utility.extension

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

// Extension function to count vowels in a String
fun String.countVowels(): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    var count = 0
    for (i in this.indices) {
        val char = this[i]
        // Check if the character is a regular vowel
        if (char in vowels) {
            count++
        }
        // Special case for 'y' - count as a vowel only if it's not the first letter
        // and if it follows a consonant or is preceded by a consonant
        else if (char.lowercaseChar() == 'y') {
            if (i > 0 && this[i - 1].isLetter() && this[i - 1] !in vowels) {
                count++
            }
        }
    }
    return count
}