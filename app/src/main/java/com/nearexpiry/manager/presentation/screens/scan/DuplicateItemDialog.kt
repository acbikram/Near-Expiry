package com.nearexpiry.manager.presentation.screens.scan

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun DuplicateItemDialog(
    existingQty: Int,
    newQty: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Same Item Found") },
        text = {
            Text(
                buildAnnotatedString {
                    append("Previous Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("$existingQty\n") }
                    append("New Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("$newQty\n") }
                    append("Final Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("${existingQty + newQty}\n") }
                    append("Confirm Save?")
                }
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
