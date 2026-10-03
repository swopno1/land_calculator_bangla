package com.vivescriptsolutions.jomirhisab.engine

import com.vivescriptsolutions.jomirhisab.model.ConversionResult
import com.vivescriptsolutions.jomirhisab.model.LandUnit
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Centralized Land Conversion Engine for Bangladesh.
 *
 * Implements high-precision, mathematically consistent conversions across
 * all standard Bangladeshi land units anchored to square feet (বর্গফুট).
 */
object LandConversionEngine {

    private val BENGALI_DIGITS = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
    private val ENGLISH_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    /**
     * Converts a given value in fromUnit to standard base Square Feet.
     */
    fun toSquareFeet(value: Double, fromUnit: LandUnit): Double {
        if (value.isNaN() || value.isInfinite() || value <= 0.0) return 0.0
        return value * fromUnit.sqFeetFactor
    }

    /**
     * Converts Square Feet to the target unit.
     */
    fun fromSquareFeet(sqFeet: Double, toUnit: LandUnit): Double {
        if (sqFeet.isNaN() || sqFeet.isInfinite() || sqFeet <= 0.0) return 0.0
        return sqFeet / toUnit.sqFeetFactor
    }

    /**
     * Converts value directly from fromUnit to toUnit.
     */
    fun convertDirect(value: Double, fromUnit: LandUnit, toUnit: LandUnit): Double {
        if (fromUnit == toUnit) return value
        val sqFt = toSquareFeet(value, fromUnit)
        return fromSquareFeet(sqFt, toUnit)
    }

    /**
     * Converts an input value from one unit into all supported land units.
     */
    fun convert(value: Double, fromUnit: LandUnit): List<ConversionResult> {
        if (value < 0.0 || value.isNaN() || value.isInfinite()) {
            return LandUnit.entries.map { unit ->
                ConversionResult(
                    unit = unit,
                    value = 0.0,
                    formattedBn = "০",
                    formattedEn = "0"
                )
            }
        }

        val baseSqFt = toSquareFeet(value, fromUnit)

        return LandUnit.entries.map { targetUnit ->
            val convertedVal = if (targetUnit == fromUnit) {
                value
            } else {
                fromSquareFeet(baseSqFt, targetUnit)
            }

            ConversionResult(
                unit = targetUnit,
                value = convertedVal,
                formattedBn = formatNumber(convertedVal, useBengaliDigits = true),
                formattedEn = formatNumber(convertedVal, useBengaliDigits = false)
            )
        }
    }

    /**
     * Parses numeric string accepting both English (0-9) and Bengali (০-৯) digits,
     * handling decimal points, commas, and whitespace gracefully.
     */
    fun parseBengaliOrEnglishNumber(rawInput: String): Double? {
        val trimmed = rawInput.trim()
        if (trimmed.isEmpty()) return null

        val sb = StringBuilder()
        var hasDecimal = false

        for (ch in trimmed) {
            when {
                ch in '0'..'9' -> sb.append(ch)
                ch in '০'..'৯' -> sb.append(ENGLISH_DIGITS[ch - '০'])
                ch == '.' || ch == '·' || ch == '৷' -> {
                    if (hasDecimal) {
                        return null // Multiple decimal points is invalid
                    }
                    sb.append('.')
                    hasDecimal = true
                }
                ch == ',' || ch == ' ' -> {
                    // Ignore comma separators and internal spacing
                    continue
                }
                else -> return null // Invalid character
            }
        }

        val normalized = sb.toString()
        if (normalized.isEmpty() || normalized == ".") return null

        return try {
            val parsed = normalized.toDouble()
            if (parsed >= 0.0 && !parsed.isNaN() && !parsed.isInfinite()) parsed else null
        } catch (_: NumberFormatException) {
            null
        }
    }

    /**
     * Formats a floating point value cleanly without trailing zeros.
     * Switches between Bengali and English digits according to preference.
     */
    fun formatNumber(value: Double, useBengaliDigits: Boolean, maxDecimals: Int = 4): String {
        if (value.isNaN() || value.isInfinite() || value == 0.0) {
            return if (useBengaliDigits) "০" else "0"
        }

        val rounded = BigDecimal.valueOf(value).setScale(maxDecimals, RoundingMode.HALF_UP)
        val plainString = rounded.stripTrailingZeros().toPlainString()

        val symbols = DecimalFormatSymbols(Locale.US)
        val pattern = if (maxDecimals == 2) "#,##0.##" else "#,##0.####"
        val df = DecimalFormat(pattern, symbols)
        val formatted = try {
            df.format(rounded)
        } catch (_: Exception) {
            plainString
        }

        return if (useBengaliDigits) {
            toBengaliDigits(formatted)
        } else {
            formatted
        }
    }

    /**
     * Converts ASCII digits (0-9) to Bengali digits (০-৯).
     */
    fun toBengaliDigits(input: String): String {
        val sb = StringBuilder(input.length)
        for (ch in input) {
            if (ch in '0'..'9') {
                sb.append(BENGALI_DIGITS[ch - '0'])
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    /**
     * Converts Bengali digits (০-৯) to ASCII digits (0-9).
     */
    fun toEnglishDigits(input: String): String {
        val sb = StringBuilder(input.length)
        for (ch in input) {
            if (ch in '০'..'৯') {
                sb.append(ENGLISH_DIGITS[ch - '০'])
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    /**
     * Builds a comprehensive text summary suitable for copying or sharing.
     */
    fun buildShareSummary(
        inputValue: Double,
        inputUnit: LandUnit,
        results: List<ConversionResult>,
        isBengali: Boolean
    ): String {
        val sb = StringBuilder()
        val formattedInput = formatNumber(inputValue, useBengaliDigits = isBengali)
        val unitName = inputUnit.getDisplayName(isBengali)

        if (isBengali) {
            sb.appendLine("📐 জমির হিসাব (Land Calculator BD)")
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("জমির পরিমাণ: $formattedInput $unitName")
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("রূপান্তরিত ফলাফল:")
            for (res in results) {
                sb.appendLine("• ${res.unit.nameBn}: ${res.formattedBn} ${res.unit.symbolBn}")
            }
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("ℹ️ মানদণ্ড: সরকারি প্রমিত হিসাব (১ বিঘা = ২০ কাঠা = ১৪,৪০০ ব.ফুট = ৩৩.০৬ শতক)।")
            sb.appendLine("অফলাইনে হিসাব করুন: জমির হিসাব অ্যাপ")
            sb.appendLine("নির্মাতা: ViveScript Solutions (https://www.vivescriptsolutions.com/)")
        } else {
            sb.appendLine("📐 Land Calculator BD (জমির হিসাব)")
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("Input Land: $formattedInput $unitName")
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("Converted Measurements:")
            for (res in results) {
                sb.appendLine("• ${res.unit.nameEn}: ${res.formattedEn} ${res.unit.symbolEn}")
            }
            sb.appendLine("━━━━━━━━━━━━━━━━━━━")
            sb.appendLine("ℹ️ Standard: 1 Bigha = 20 Katha = 14,400 sq ft = 33.06 Decimals.")
            sb.appendLine("100% Offline Calculator")
            sb.appendLine("Developer: ViveScript Solutions (https://www.vivescriptsolutions.com/)")
        }

        return sb.toString()
    }
}
