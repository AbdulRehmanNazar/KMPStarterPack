package com.starter.app.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.Font
import starterpack.composeapp.generated.resources.Res
import starterpack.composeapp.generated.resources.*


@Composable
fun AppFontFamily() = FontFamily(
    Font(Res.font.Ubuntu_Light, weight = FontWeight.Light),
    Font(Res.font.Ubuntu_Regular, weight = FontWeight.Normal),
    Font(Res.font.Ubuntu_Medium, weight = FontWeight.Medium),
    Font(Res.font.Ubuntu_Bold, weight = FontWeight.Bold)
)

// Define custom Typography using your font family

@Composable
fun AppTypography() = Typography(
    displayLarge = TextStyle(
        fontSize = 30.ssp,
        fontWeight = FontWeight.Medium,
        lineHeight = 36.ssp,
        color = AppColors.Typography500
    ),
    bodyLarge = TextStyle(
        fontSize = 12.ssp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.ssp,
        color = AppColors.Typography500
    ),
    // Define other styles like titleMedium, labelSmall, etc., if needed
//    defaultFontFamily = AppFontFamily()
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = AppTypography(),
        content = content
    )
}