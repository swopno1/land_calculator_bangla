package com.vivescriptsolutions.jomirhisab.model

/**
 * Bangladesh Land Measurement Units.
 *
 * Centralized definition of all supported land and area units.
 * Base conversion standard: Square Feet (বর্গফুট).
 *
 * Official Bangladesh Standard:
 * - 1 Decimal (শতাংশ) = 435.60 Square Feet
 * - 1 Katha (কাঠা)   = 720.00 Square Feet (16 Chattak)
 * - 1 Bigha (বিঘা)    = 14,400.00 Square Feet (20 Katha ≈ 33.06 decimals)
 * - 1 Acre (একর)      = 43,560.00 Square Feet (100 decimals = 3.025 Bighas)
 * - 1 Hectare (হেক্টর) = 10,000 Square Meters ≈ 107,639.104 Square Feet
 * - 1 Chattak (ছটাক)  = 45.00 Square Feet (1/16 Katha)
 * - 1 Ganda (গণ্ডা)   = 2.25 Square Feet (1/20 Chattak)
 * - 1 Sq Yard (বর্গগজ) = 9.00 Square Feet
 * - 1 Sq Meter (বর্গমিটার) = 10.7639104 Square Feet
 */
enum class LandUnit(
    val id: String,
    val nameBn: String,
    val nameEn: String,
    val symbolBn: String,
    val symbolEn: String,
    val sqFeetFactor: Double,
    val descriptionBn: String,
    val descriptionEn: String,
    val isPrimary: Boolean = true
) {
    DECIMAL(
        id = "decimal",
        nameBn = "শতাংশ / শতক",
        nameEn = "Decimal / Shotangsho",
        symbolBn = "শতাংশ",
        symbolEn = "decimal",
        sqFeetFactor = 435.60,
        descriptionBn = "১ শতাংশ = ৪৩৫.৬০ বর্গফুট",
        descriptionEn = "1 Decimal = 435.60 sq ft",
        isPrimary = true
    ),
    KATHA(
        id = "katha",
        nameBn = "কাঠা",
        nameEn = "Katha",
        symbolBn = "কাঠা",
        symbolEn = "katha",
        sqFeetFactor = 720.00,
        descriptionBn = "১ কাঠা = ৭২০ বর্গফুট (১.৬৫ শতাংশ)",
        descriptionEn = "1 Katha = 720 sq ft (1.65 dec)",
        isPrimary = true
    ),
    BIGHA(
        id = "bigha",
        nameBn = "বিঘা (প্রমিত ৩৩ শতক)",
        nameEn = "Bigha (Standard 33 Dec)",
        symbolBn = "বিঘা",
        symbolEn = "bigha",
        sqFeetFactor = 14400.00,
        descriptionBn = "১ বিঘা = ২০ কাঠা = ১৪,৪০০ বর্গফুট (৩৩.০৬ শতাংশ)",
        descriptionEn = "1 Bigha = 20 Katha = 14,400 sq ft (33.06 dec)",
        isPrimary = true
    ),
    ACRE(
        id = "acre",
        nameBn = "একর",
        nameEn = "Acre",
        symbolBn = "একর",
        symbolEn = "acre",
        sqFeetFactor = 43560.00,
        descriptionBn = "১ একর = ১০০ শতাংশ = ৩.০২৫ বিঘা = ৪৩,৫৬০ বর্গফুট",
        descriptionEn = "1 Acre = 100 Decimals = 3.025 Bigha",
        isPrimary = true
    ),
    SQUARE_FEET(
        id = "sqft",
        nameBn = "বর্গফুট",
        nameEn = "Square Feet",
        symbolBn = "ব.ফুট",
        symbolEn = "sq ft",
        sqFeetFactor = 1.00,
        descriptionBn = "প্রমিত ভিত্তি একক",
        descriptionEn = "Standard base unit",
        isPrimary = true
    ),
    SQUARE_METER(
        id = "sqm",
        nameBn = "বর্গমিটার",
        nameEn = "Square Meter",
        symbolBn = "ব.মিটার",
        symbolEn = "sq m",
        sqFeetFactor = 10.763910416709722,
        descriptionBn = "১ বর্গমিটার = ১০.৭৬৪ বর্গফুট",
        descriptionEn = "1 Sq Meter = 10.764 sq ft",
        isPrimary = true
    ),
    SQUARE_YARD(
        id = "sqyard",
        nameBn = "বর্গগজ (গজ²)",
        nameEn = "Square Yard",
        symbolBn = "ব.গজ",
        symbolEn = "sq yd",
        sqFeetFactor = 9.00,
        descriptionBn = "১ বর্গগজ = ৯ বর্গফুট",
        descriptionEn = "1 Sq Yard = 9 sq ft",
        isPrimary = false
    ),
    HECTARE(
        id = "hectare",
        nameBn = "হেক্টর",
        nameEn = "Hectare",
        symbolBn = "হেক্টর",
        symbolEn = "ha",
        sqFeetFactor = 107639.10416709722,
        descriptionBn = "১ হেক্টর = ১০,০০০ বর্গমিটার = ২.৪৭ একর",
        descriptionEn = "1 Hectare = 10,000 sq m = 2.471 Acre",
        isPrimary = false
    ),
    CHATTAK(
        id = "chattak",
        nameBn = "ছটাক",
        nameEn = "Chattak",
        symbolBn = "ছটাক",
        symbolEn = "chattak",
        sqFeetFactor = 45.00,
        descriptionBn = "১ ছটাক = ৪৫ বর্গফুট (১/১৬ কাঠা)",
        descriptionEn = "1 Chattak = 45 sq ft (1/16 Katha)",
        isPrimary = false
    ),
    GANDA(
        id = "ganda",
        nameBn = "গণ্ডা",
        nameEn = "Ganda",
        symbolBn = "গণ্ডা",
        symbolEn = "ganda",
        sqFeetFactor = 2.25,
        descriptionBn = "১ গণ্ডা = ২.২৫ বর্গফুট (১/২০ ছটাক)",
        descriptionEn = "1 Ganda = 2.25 sq ft (1/20 Chattak)",
        isPrimary = false
    );

    fun getDisplayName(isBengali: Boolean): String = if (isBengali) nameBn else nameEn
    fun getDisplaySymbol(isBengali: Boolean): String = if (isBengali) symbolBn else symbolEn
    fun getDescription(isBengali: Boolean): String = if (isBengali) descriptionBn else descriptionEn
}

data class ConversionResult(
    val unit: LandUnit,
    val value: Double,
    val formattedBn: String,
    val formattedEn: String
)
