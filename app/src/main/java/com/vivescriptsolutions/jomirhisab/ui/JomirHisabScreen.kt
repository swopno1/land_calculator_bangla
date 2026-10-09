package com.vivescriptsolutions.jomirhisab.ui

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivescriptsolutions.jomirhisab.ads.AdManager
import com.vivescriptsolutions.jomirhisab.ads.AdMobBannerView
import com.vivescriptsolutions.jomirhisab.engine.LandConversionEngine
import com.vivescriptsolutions.jomirhisab.model.ConversionResult
import com.vivescriptsolutions.jomirhisab.model.LandUnit
import com.vivescriptsolutions.jomirhisab.ui.theme.PrimaryGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun JomirHisabScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // App language state (true = Bengali, false = English)
    var isBengali by rememberSaveable { mutableStateOf(true) }

    // Input state
    var inputText by rememberSaveable { mutableStateOf("1") }
    var selectedUnit by rememberSaveable { mutableStateOf(LandUnit.DECIMAL) }

    // Bottom sheet state for info & disclaimer
    var showInfoSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Number format toggle (show Bengali digits vs English digits in results)
    var showBengaliNumerals by rememberSaveable { mutableStateOf(true) }

    // Parse input and compute conversions live
    val parsedValue = remember(inputText) {
        LandConversionEngine.parseBengaliOrEnglishNumber(inputText)
    }

    val conversionResults: List<ConversionResult> = remember(parsedValue, selectedUnit) {
        if (parsedValue != null && parsedValue > 0.0) {
            LandConversionEngine.convert(parsedValue, selectedUnit)
        } else {
            emptyList()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Landscape,
                                    contentDescription = "App Icon",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (isBengali) "জমির হিসাব" else "Land Calculator BD",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isBengali) "বাংলাদেশের সহজ জমির পরিমাপ" else "Bangladesh Land Measurement",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    // Language Switcher Chip
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier
                            .testTag("lang_toggle_button")
                            .clickable {
                                isBengali = !isBengali
                                showBengaliNumerals = isBengali
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Change Language",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isBengali) "বাং / EN" else "EN / বাং",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Info / Standards Button
                    IconButton(
                        onClick = { showInfoSheet = true },
                        modifier = Modifier.testTag("info_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = if (isBengali) "মাপের সূত্র ও তথ্য" else "Standards & Info",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            AdMobBannerView()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 680.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Offline Badge
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isBengali)
                                "অফলাইন গণক • জমি পরিমাপ ও রূপান্তর"
                            else
                                "Offline Engine • Land Measurement & Conversion",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Input Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isBengali) "জমির পরিমাণ লিখুন" else "Enter Land Quantity",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            // Numeral toggle (বাংলা / 123)
                            TextButton(
                                onClick = { showBengaliNumerals = !showBengaliNumerals },
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text(
                                    text = if (showBengaliNumerals) "অংক: ১২৩" else "Digits: 123",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Large Numeric Input Field
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_field"),
                            placeholder = {
                                Text(if (isBengali) "যেমন: ১০.৫" else "e.g. 10.5")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Calculate,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            trailingIcon = {
                                if (inputText.isNotEmpty()) {
                                    IconButton(
                                        onClick = { inputText = "" },
                                        modifier = Modifier.testTag("clear_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = if (isBengali) "মুছুন" else "Clear",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            textStyle = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Unit Selection Section (Unified Fast Quick Select)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isBengali) "পরিমাপের একক:" else "Measurement Unit:",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.padding(horizontal = 2.dp)
                            ) {
                                Text(
                                    text = selectedUnit.getDisplayName(isBengali),
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("unit_selector")
                        ) {
                            LandUnit.entries.forEach { unit ->
                                val isSelected = unit == selectedUnit
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { selectedUnit = unit },
                                    label = {
                                        Text(
                                            text = unit.getDisplayName(isBengali),
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    leadingIcon = if (isSelected) {
                                        {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    } else null,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                        selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    modifier = Modifier.testTag("unit_chip_${unit.id}")
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Results Header and Action Buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = if (isBengali) "রূপান্তরিত ফলাফল" else "Conversion Results",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (parsedValue != null && parsedValue > 0.0) {
                            val formattedInput = LandConversionEngine.formatNumber(
                                parsedValue,
                                useBengaliDigits = showBengaliNumerals
                            )
                            Text(
                                text = "$formattedInput ${selectedUnit.getDisplayName(isBengali)} =",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    if (conversionResults.isNotEmpty() && parsedValue != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Copy All Button
                            TextButton(
                                onClick = {
                                    val summary = LandConversionEngine.buildShareSummary(
                                        inputValue = parsedValue,
                                        inputUnit = selectedUnit,
                                        results = conversionResults,
                                        isBengali = isBengali
                                    )
                                    copyToClipboard(context, summary)
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            if (isBengali) "সব ফলাফল কপি হয়েছে" else "All results copied"
                                        )
                                    }
                                },
                                modifier = Modifier.testTag("copy_all_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isBengali) "কপি" else "Copy All",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }

                            // Share Button
                            IconButton(
                                onClick = {
                                    val summary = LandConversionEngine.buildShareSummary(
                                        inputValue = parsedValue,
                                        inputUnit = selectedUnit,
                                        results = conversionResults,
                                        isBengali = isBengali
                                    )
                                    val activity = context as? Activity
                                    if (activity != null) {
                                        AdManager.showInterstitial(activity) {
                                            shareText(context, summary, if (isBengali) "জমির হিসাব শেয়ার" else "Share Land Calculation")
                                        }
                                    } else {
                                        shareText(context, summary, if (isBengali) "জমির হিসাব শেয়ার" else "Share Land Calculation")
                                    }
                                },
                                modifier = Modifier.testTag("share_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = if (isBengali) "শেয়ার করুন" else "Share",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Results List
                if (conversionResults.isEmpty() || parsedValue == null || parsedValue <= 0.0) {
                    // Empty or invalid input state
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (inputText.trim().isEmpty()) {
                                    if (isBengali) "জমির পরিমাণ লিখে ফলাফল দেখুন" else "Enter a land amount to see conversions"
                                } else {
                                    if (isBengali) "সঠিক সংখ্যা লিখুন (যেমন: ৫ বা ১২.৫)" else "Please enter a valid number (e.g. 5 or 12.5)"
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    // Results Cards
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        conversionResults.forEach { result ->
                            val isSourceUnit = result.unit == selectedUnit
                            val displayValue = if (showBengaliNumerals) result.formattedBn else result.formattedEn

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSourceUnit) {
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                                    } else {
                                        MaterialTheme.colorScheme.surface
                                    }
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = if (isSourceUnit) 3.dp else 1.dp
                                )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp, vertical = 12.dp)
                                ) {
                                    // Unit Title & Subtitle
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = result.unit.getDisplayName(isBengali),
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSourceUnit) {
                                                    MaterialTheme.colorScheme.onPrimaryContainer
                                                } else {
                                                    MaterialTheme.colorScheme.onSurface
                                                }
                                            )
                                            if (isSourceUnit) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Surface(
                                                    shape = RoundedCornerShape(6.dp),
                                                    color = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.padding(horizontal = 2.dp)
                                                ) {
                                                    Text(
                                                        text = if (isBengali) "মূল একক" else "Input",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = MaterialTheme.colorScheme.onPrimary,
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                    )
                                                }
                                            }
                                        }

                                        Text(
                                            text = result.unit.getDescription(isBengali),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    // Converted Value & Copy Button
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = displayValue,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSourceUnit) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.onSurface
                                            }
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = result.unit.getDisplaySymbol(isBengali),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        Spacer(modifier = Modifier.width(6.dp))

                                        IconButton(
                                            onClick = {
                                                val copyText = "$displayValue ${result.unit.getDisplayName(isBengali)}"
                                                copyToClipboard(context, copyText)
                                                scope.launch {
                                                    snackbarHostState.showSnackbar(
                                                        if (isBengali) "$copyText কপি হয়েছে" else "Copied: $copyText"
                                                    )
                                                }
                                            },
                                            modifier = Modifier.size(36.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ContentCopy,
                                                contentDescription = if (isBengali) "কপি" else "Copy",
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Standard Formulae & Disclaimer Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isBengali) "বাংলাদেশে প্রচলিত জমির হিসাবের সূত্র" else "Standard BD Land Measurement Formulae",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val formulaItems = if (isBengali) {
                            listOf(
                                "১ শতাংশ / শতক = ৪৩৫.৬০ বর্গফুট (৪৮.৪০ বর্গগজ)",
                                "১ কাঠা = ৭২০ বর্গফুট (১.৬৫ শতাংশ / শতক)",
                                "১ বিঘা = ২০ কাঠা = ১৪,৪০০ বর্গফুট (৩৩.০৬ শতক)",
                                "১ একর = ১০০ শতাংশ = ৩.০২৫ বিঘা = ৪৩,৫৬০ বর্গফুট",
                                "১ হেক্টর = ১০,০০০ বর্গমিটার = ২.৪৭১ একর",
                                "১ ছটাক = ৪৫ বর্গফুট (১৬ ভাগের ১ কাঠা)",
                                "১ বর্গমিটার = ১০.৭৬৪ বর্গফুট"
                            )
                        } else {
                            listOf(
                                "1 Decimal / Shotok = 435.60 sq ft (48.40 sq yards)",
                                "1 Katha = 720.00 sq ft (1.65 Decimals)",
                                "1 Bigha = 20 Katha = 14,400 sq ft (33.06 Decimals)",
                                "1 Acre = 100 Decimals = 3.025 Bigha = 43,560 sq ft",
                                "1 Hectare = 10,000 sq meters = 2.471 Acres",
                                "1 Chattak = 45.00 sq ft (1/16 Katha)",
                                "1 Sq Meter = 10.764 sq ft"
                            )
                        }

                        formulaItems.forEach { formula ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.padding(vertical = 3.dp)
                            ) {
                                Text(
                                    text = "• ",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = formula,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                        )

                        // Disclaimer Note
                        Text(
                            text = if (isBengali)
                                "⚠️ সতর্কতা: কাঠা ও বিঘার মাপ অঞ্চলভেদে ভিন্ন হতে পারে (যেমন কিছু অঞ্চলে ৩৩, ৪০ বা ৫০ শতকে বিঘা ধরা হয়)। এই অ্যাপে বাংলাদেশ সরকারের প্রচলিত প্রমিত পরিমাপ মান ব্যবহার করা হয়েছে।"
                            else
                                "⚠️ Notice: Katha and Bigha measurements can vary by region (e.g. 33, 40, or 50 decimals per Bigha). This app uses the standard Bangladesh government survey standard (1 Bigha = 20 Katha = 14,400 sq ft).",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Footer
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "ViveScript Solutions",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (isBengali) "সংস্করণ ৫.০ • www.vivescriptsolutions.com" else "Version 5.0 • www.vivescriptsolutions.com",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    // Modal Bottom Sheet with Comprehensive Information & Regional Details
    if (showInfoSheet) {
        ModalBottomSheet(
            onDismissRequest = { showInfoSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Landscape,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (isBengali) "জমির পরিমাপের পূর্ণাঙ্গ বিবরণ" else "Complete Land Measurement Guide",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isBengali) "বাংলাদেশ সরকারের প্রমিত এককসমূহ:" else "Bangladesh Government Standard Units:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                val detailedStandards = if (isBengali) {
                    listOf(
                        "১ শতাংশ (শতক / ডেসিমেল) = ৪৩৫.৬০ বর্গফুট = ৪৮.৪০ বর্গগজ = ৪০.৪৭ বর্গমিটার",
                        "১ কাঠা = ৭২০ বর্গফুট = ৮০ বর্গগজ = ১৬ ছটাক ≈ ১.৬৫২৮৯ শতাংশ",
                        "১ বিঘা = ২০ কাঠা = ১৪,৪০০ বর্গফুট = ১৬০০ বর্গগজ ≈ ৩৩.০৬ শতাংশ (৩৩ শতক)",
                        "১ একর = ১০০ শতাংশ = ৩ বিঘা ৮ ছটাক (৩.০২৫ বিঘা) = ৬০.৫ কাঠা = ৪৩,৫৬০ বর্গফুট",
                        "১ হেক্টর = ১০,০০০ বর্গমিটার = ২.৪৭১ একর = ২৪৭.১০ শতাংশ",
                        "১ ছটাক = ৪৫ বর্গফুট = ৫ বর্গগজ = ২০ গণ্ডা",
                        "১ গণ্ডা = ২.২৫ বর্গফুট = ৪ কড়া",
                        "১ কড়া = ০.৭৫ বর্গফুট = ৩ ক্রান্তি",
                        "১ ক্রান্তি = ০.২৫ বর্গফুট = ২০ তিল",
                        "১ বর্গমিটার = ১০.৭৬৪ বর্গফুট = ১.১৯৬ বর্গগজ"
                    )
                } else {
                    listOf(
                        "1 Decimal (Shotangsho / Shotok) = 435.60 sq ft = 48.40 sq yd = 40.47 sq m",
                        "1 Katha = 720 sq ft = 80 sq yd = 16 Chattak ≈ 1.65289 Decimals",
                        "1 Bigha = 20 Katha = 14,400 sq ft = 1,600 sq yd ≈ 33.06 Decimals",
                        "1 Acre = 100 Decimals = 3.025 Bigha = 60.5 Katha = 43,560 sq ft",
                        "1 Hectare = 10,000 sq m = 2.471 Acres = 247.10 Decimals",
                        "1 Chattak = 45 sq ft = 5 sq yd = 20 Ganda",
                        "1 Ganda = 2.25 sq ft = 4 Kora",
                        "1 Kora = 0.75 sq ft = 3 Kranti",
                        "1 Kranti = 0.25 sq ft = 20 Til",
                        "1 Sq Meter = 10.764 sq ft = 1.196 sq yd"
                    )
                }

                detailedStandards.forEach { item ->
                    Text(
                        text = "• $item",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isBengali) "অঞ্চলভেদে প্রচলিত স্থানীয় প্রথা:" else "Regional Variations in Bangladesh:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isBengali)
                        "বাংলাদেশে প্রমিত সরকারি হিসেবে ১ বিঘা = ৩৩.০৬ শতাংশ (৩৩ শতক)। তবে কিছু অঞ্চলে যেমন রাজশাহী বা উত্তরাঞ্চলের কোনো কোনো এলাকায় ৪০, ৫০ বা ৬০ শতকে বিঘা হিসাব করার প্রাচীন প্রথা রয়েছে। এছাড়া সিলেট অঞ্চলে 'কিয়ার' (সাধারণত ১২ থেকে ৩০ শতক) এবং চট্টগ্রাম ও নোয়াখালী অঞ্চলে 'কানি' (৪০ শতক থেকে ১৬০ শতকের শাহী কানি) প্রচলিত আছে। সরকারি দলিল ও রেকর্ডে সর্বদা শতাংশ/একরে হিসাব লেখা হয়।"
                    else
                        "While the standard government definition is 1 Bigha = 33.06 decimals (33 shotok), some northern regions traditionally used 40, 50, or 60 decimal Bighas. In Sylhet, 'Kiyar' is used, and in Chittagong/Noakhali, 'Kani' (40 to 160 decimals) exists in traditional parlance. Official government deeds always record measurements in Decimals and Acres.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isBengali) "আইনগত দায়মুক্তি (Disclaimer):" else "Legal Disclaimer:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isBengali)
                        "এই অ্যাপটি সাধারণ হিসাব ও তাৎক্ষণিক রূপান্তরের সুবিধার্থে তৈরি। জমি ক্রয়-বিক্রয়, রেজিস্ট্রি, সীমানা নির্ধারণ বা যেকোনো আইনি কাজের পূর্বে অবশ্যই অনুমোদিত সরকারি আমিন/সার্ভেয়ার এবং সংশ্লিষ্ট ভূমি অফিসের প্রামাণ্য রেকর্ড যাচাই করুন।"
                    else
                        "This app is intended for general informational calculation and convenient unit conversion. For real estate transactions, registration, surveying, or legal proceedings, always verify with licensed professional surveyors and official government land records.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { showInfoSheet = false },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isBengali) "ঠিক আছে" else "Close")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Land Calculation", text)
    clipboard.setPrimaryClip(clip)
}

private fun shareText(context: Context, text: String, title: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, title))
}
