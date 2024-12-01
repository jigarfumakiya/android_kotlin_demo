package com.app.android_test.helpers

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

// Function to perform safe division
fun safeDivide(a: Int?, b: Int?): Int? {
    return if (a != null && b != null) {
        if (b == 0) null // Avoid division by zero
        else a / b
    } else {
        null
    }
}


 
