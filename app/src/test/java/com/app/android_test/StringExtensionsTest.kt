package com.app.android_test

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

import com.app.android_test.core.utility.extension.countVowels
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun testCountVowels_AllVowels() {
        val result = "aeiou".countVowels()
        assertEquals(5, result)
    }

    @Test
    fun testCountVowels_MixedCaseVowels() {
        val result = "AeIoU".countVowels()
        assertEquals(5, result)
    }

    @Test
    fun testCountVowels_WithConsonants() {
        val result = "hello world".countVowels()
        assertEquals(3, result) // 'e', 'o', 'o'
    }

    @Test
    fun testCountVowels_NoVowels() {
        val result = "bcdfg".countVowels()
        assertEquals(0, result)
    }

    @Test
    fun testCountVowels_EmptyString() {
        val result = "".countVowels()
        assertEquals(0, result)
    }

    @Test
    fun testCountVowels_WithNumbersAndSymbols() {
        val result = "123 !@#".countVowels()
        assertEquals(0, result)
    }

    @Test
//    AEIOU
//    i,i,a,i,a,e,i,o,o,y
//    i,i,a,o,i,i,a,y,o,e
//    i,i,a,o,i,i,a,o,e
    fun testCountVowels_LongString() {
        val result = "This is a long string with many vowels!".countVowels()
        assertEquals(10, result) // Counts all vowels in the string
    }
}
