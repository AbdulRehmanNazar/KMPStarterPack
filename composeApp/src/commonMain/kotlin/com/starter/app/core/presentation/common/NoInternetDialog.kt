package com.starter.app.core.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.starter.app.core.presentation.theme.AppColors
import kmpstarterpack.composeapp.generated.resources.*
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoInternetDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    title: String = stringResource(Res .string.outside_geofenced),
    message: String = stringResource(Res.string.you_are_not_within),
    confirmButtonText: String = stringResource(Res.string.yes),
    cancelButtonText: String = stringResource(Res.string.cancel),
    iconResource: Painter? = null,
    iconTint: Color = Color.Unspecified,
    iconBackgroundColor: Color = AppColors.Primary50
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.sdp, vertical = 16.sdp),
                shape = RoundedCornerShape(8.sdp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.sdp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.sdp, start = 16.sdp, end = 16.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Icon with circular background
                        iconResource?.let { icon ->
                            Box(
                                modifier = Modifier
                                    .size(40.sdp)
                                    .background(
                                        color = iconBackgroundColor,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = icon,
                                    contentDescription = null,
                                    tint = iconTint,
                                    modifier = Modifier.size(15.sdp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.sdp))
                        }

                        // Title
                        Text(
                            text = title,
                            fontSize = 15.ssp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.Typography500,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.sdp))

                        // Message
                        Text(
                            text = message,
                            fontSize = 13.ssp,
                            color = AppColors.Typography500,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.ssp
                        )

                        //  Spacer(modifier = Modifier.height(10.sdp))


                    }
                    // Buttons
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(14.sdp)
                    ) {
                        // Confirm button (Primary)
                        Button(
                            onClick = {
                                onConfirm()
                                onDismiss()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.sdp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.Primary600
                            ),
                            shape = RoundedCornerShape(8.sdp)
                        ) {
                            Text(
                                text = confirmButtonText,
                                color = Color.White,
                                fontSize = 16.ssp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(8.sdp))
                    }
                }
            }
        }
    }
}