package ag.plazz.standardtest

import org.junit.Assert
import org.junit.Test

/**
 *   @Author Paul Cech
 *   Created on 05.11.18
 */
class CalculatorTest {

    @Test
    fun multiply_check1() {
        val result = Calculator.multiplyCorrect(3, 4)
        val expectedResult = 12

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun multiply_check2() {
        val result = Calculator.multiplyFixed(3, 4)
        val expectedResult = 12

        Assert.assertEquals(expectedResult, result)
    }
}