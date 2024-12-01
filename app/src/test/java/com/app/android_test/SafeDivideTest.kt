package com.app.android_test

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

import com.app.android_test.core.utility.safeDivide
import org.junit.Assert.*
import org.junit.Test

class SafeDivideTest {

    @Test
    fun testSafeDivideBothNonNull() {
        val result = safeDivide(10, 2)
        assertEquals(5, result)
    }

    @Test
    fun testSafeDivideWithNullFirstParam() {
        val result = safeDivide(null, 2)
        assertNull(result)
    }

    @Test
    fun testSafeDivideWithNullSecondParam() {
        val result = safeDivide(10, null)
        assertNull(result)
    }

    @Test
    fun testSafeDivideWithBothParamsNull() {
        val result = safeDivide(null, null)
        assertNull(result)
    }

    @Test
    fun testSafeDivideByZero() {
        val result = safeDivide(10, 0)
        assertNull(result) // Division by zero should return null
    }
}
