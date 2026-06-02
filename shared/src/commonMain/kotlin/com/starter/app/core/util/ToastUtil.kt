package com.starter.app.core.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.starter.app.core.presentation.theme.AppColors
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.ToastGravity
import multiplatform.network.cmptoast.showToast

object ToastUtil {

    /**
     * Shows a toast message
     * @param message to show on Toast
     */
    fun showToastMessage(message: String) {
        showToast(
            message = message,
            backgroundColor = AppColors.ToastBackGround,
            textColor = Color.White,
            textSize = 16.sp,
            duration = ToastDuration.Short,
            gravity = ToastGravity.Bottom,
            bottomPadding = 50
        )
    }
}