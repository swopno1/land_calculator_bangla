package com.vivescriptsolutions.jomirhisab.engine

import com.vivescriptsolutions.jomirhisab.model.LandUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Automated Unit Tests for Bangladesh Land Conversion Engine.
 */
class LandConversionEngineTest {

    private val DELTA = 0.0001

    @Test
    fun testOneDecimalConversions() {
        // 1 Decimal = 435.60 Square Feet
        val sqFt = LandConversionEngine.convertDirect(1.0, LandUnit.DECIMAL, LandUnit.SQUARE_FEET)
        assertEquals(435.60, sqFt, DELTA)

        // 1 Decimal to Square Yards (435.6 / 9 = 48.40)
        val sqYd = LandConversionEngine.convertDirect(1.0, LandUnit.DECIMAL, LandUnit.SQUARE_YARD)
        assertEquals(48.40, sqYd, DELTA)

        // 100 Decimals = 1 Acre
        val acre = LandConversionEngine.convertDirect(100.0, LandUnit.DECIMAL, LandUnit.ACRE)
        assertEquals(1.0, acre, DELTA)

        // Reverse: 1 Acre = 100 Decimals
        val decimalsFromAcre = LandConversionEngine.convertDirect(1.0, LandUnit.ACRE, LandUnit.DECIMAL)
        assertEquals(100.0, decimalsFromAcre, DELTA)
    }

    @Test
    fun testOneKathaConversions() {
        // 1 Katha = 720.00 Square Feet
        val sqFt = LandConversionEngine.convertDirect(1.0, LandUnit.KATHA, LandUnit.SQUARE_FEET)
        assertEquals(720.00, sqFt, DELTA)

        // 1 Katha = 80 Square Yards (720 / 9)
        val sqYd = LandConversionEngine.convertDirect(1.0, LandUnit.KATHA, LandUnit.SQUARE_YARD)
        assertEquals(80.0, sqYd, DELTA)

        // 1 Katha = 16 Chattak
        val chattak = LandConversionEngine.convertDirect(1.0, LandUnit.KATHA, LandUnit.CHATTAK)
        assertEquals(16.0, chattak, DELTA)

        // 1 Katha ≈ 1.65289256 Decimal (720 / 435.6)
        val decimal = LandConversionEngine.convertDirect(1.0, LandUnit.KATHA, LandUnit.DECIMAL)
        assertEquals(720.0 / 435.6, decimal, DELTA)
    }

    @Test
    fun testOneBighaConversions() {
        // 1 Bigha = 20 Katha
        val katha = LandConversionEngine.convertDirect(1.0, LandUnit.BIGHA, LandUnit.KATHA)
        assertEquals(20.0, katha, DELTA)

        // 1 Bigha = 14,400.00 Square Feet
        val sqFt = LandConversionEngine.convertDirect(1.0, LandUnit.BIGHA, LandUnit.SQUARE_FEET)
        assertEquals(14400.0, sqFt, DELTA)

        // 1 Bigha = 1600 Square Yards (14,400 / 9)
        val sqYd = LandConversionEngine.convertDirect(1.0, LandUnit.BIGHA, LandUnit.SQUARE_YARD)
        assertEquals(1600.0, sqYd, DELTA)

        // 1 Bigha ≈ 33.05785 Decimals (Standard 33 শতক)
        val decimal = LandConversionEngine.convertDirect(1.0, LandUnit.BIGHA, LandUnit.DECIMAL)
        assertEquals(14400.0 / 435.6, decimal, DELTA)
    }

    @Test
    fun testOneAcreConversions() {
        // 1 Acre = 43,560 Square Feet
        val sqFt = LandConversionEngine.convertDirect(1.0, LandUnit.ACRE, LandUnit.SQUARE_FEET)
        assertEquals(43560.0, sqFt, DELTA)

        // 1 Acre = 4,840 Square Yards
        val sqYd = LandConversionEngine.convertDirect(1.0, LandUnit.ACRE, LandUnit.SQUARE_YARD)
        assertEquals(4840.0, sqYd, DELTA)

        // 1 Acre = 3.025 Bighas (3 bigha 8 chattak)
        val bigha = LandConversionEngine.convertDirect(1.0, LandUnit.ACRE, LandUnit.BIGHA)
        assertEquals(3.025, bigha, DELTA)

        // 1 Acre = 60.5 Kathas
        val katha = LandConversionEngine.convertDirect(1.0, LandUnit.ACRE, LandUnit.KATHA)
        assertEquals(60.5, katha, DELTA)
    }

    @Test
    fun testHectareConversions() {
        // 1 Hectare = 10,000 Square Meters
        val sqMeter = LandConversionEngine.convertDirect(1.0, LandUnit.HECTARE, LandUnit.SQUARE_METER)
        assertEquals(10000.0, sqMeter, DELTA)

        // 1 Hectare ≈ 2.471 Acres
        val acre = LandConversionEngine.convertDirect(1.0, LandUnit.HECTARE, LandUnit.ACRE)
        assertEquals(2.47105, acre, 0.001)
    }

    @Test
    fun testSubUnitsChattakAndGanda() {
        // 1 Chattak = 45 sq ft
        val chattakSqFt = LandConversionEngine.convertDirect(1.0, LandUnit.CHATTAK, LandUnit.SQUARE_FEET)
        assertEquals(45.0, chattakSqFt, DELTA)

        // 1 Chattak = 20 Ganda
        val ganda = LandConversionEngine.convertDirect(1.0, LandUnit.CHATTAK, LandUnit.GANDA)
        assertEquals(20.0, ganda, DELTA)

        // 1 Ganda = 2.25 sq ft
        val gandaSqFt = LandConversionEngine.convertDirect(1.0, LandUnit.GANDA, LandUnit.SQUARE_FEET)
        assertEquals(2.25, gandaSqFt, DELTA)
    }

    @Test
    fun testReverseConversionsAccuracy() {
        val originalDecimal = 15.75
        val toKatha = LandConversionEngine.convertDirect(originalDecimal, LandUnit.DECIMAL, LandUnit.KATHA)
        val backToDecimal = LandConversionEngine.convertDirect(toKatha, LandUnit.KATHA, LandUnit.DECIMAL)
        assertEquals(originalDecimal, backToDecimal, 0.000001)

        val toBigha = LandConversionEngine.convertDirect(originalDecimal, LandUnit.DECIMAL, LandUnit.BIGHA)
        val backFromBigha = LandConversionEngine.convertDirect(toBigha, LandUnit.BIGHA, LandUnit.DECIMAL)
        assertEquals(originalDecimal, backFromBigha, 0.000001)
    }

    @Test
    fun testZeroAndNegativeInputs() {
        val zeroResult = LandConversionEngine.convert(0.0, LandUnit.DECIMAL)
        assertTrue(zeroResult.all { it.value == 0.0 })

        val negResult = LandConversionEngine.convert(-5.0, LandUnit.DECIMAL)
        assertTrue(negResult.all { it.value == 0.0 })
    }

    @Test
    fun testVeryLargeInputs() {
        val largeAcre = 1_000_000.0 // 1 Million Acres
        val sqFt = LandConversionEngine.convertDirect(largeAcre, LandUnit.ACRE, LandUnit.SQUARE_FEET)
        assertEquals(43_560_000_000.0, sqFt, 1.0)
    }

    @Test
    fun testBengaliNumberParsing() {
        // Bengali numerals "১০.৫"
        val parsedBn = LandConversionEngine.parseBengaliOrEnglishNumber("১০.৫")
        assertNotNull(parsedBn)
        assertEquals(10.5, parsedBn!!, DELTA)

        // English numerals "10.5"
        val parsedEn = LandConversionEngine.parseBengaliOrEnglishNumber("10.5")
        assertNotNull(parsedEn)
        assertEquals(10.5, parsedEn!!, DELTA)

        // Mixed / with spaces and commas " ১,২৩৪.৫৬ "
        val parsedMixed = LandConversionEngine.parseBengaliOrEnglishNumber(" ১,২৩৪.৫৬ ")
        assertNotNull(parsedMixed)
        assertEquals(1234.56, parsedMixed!!, DELTA)

        // Invalid inputs
        assertNull(LandConversionEngine.parseBengaliOrEnglishNumber(""))
        assertNull(LandConversionEngine.parseBengaliOrEnglishNumber("abc"))
        assertNull(LandConversionEngine.parseBengaliOrEnglishNumber("১.২.৩"))
        assertNull(LandConversionEngine.parseBengaliOrEnglishNumber(".."))
    }

    @Test
    fun testBengaliDigitFormatting() {
        val formattedBn = LandConversionEngine.formatNumber(12.34, useBengaliDigits = true)
        assertEquals("১২.৩৪", formattedBn)

        val formattedEn = LandConversionEngine.formatNumber(12.34, useBengaliDigits = false)
        assertEquals("12.34", formattedEn)

        val zeroBn = LandConversionEngine.formatNumber(0.0, useBengaliDigits = true)
        assertEquals("০", zeroBn)
    }
}
