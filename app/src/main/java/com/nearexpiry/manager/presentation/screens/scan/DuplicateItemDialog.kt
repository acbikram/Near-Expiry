package com.nearexpiry.manager.presentation.screens.scan

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun DuplicateItemDialog(
    existingQty: Double,
    newQty: Double,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val formatQty: (Double) -> String = { qty ->
        if (qty % 1.0 == 0.0) qty.toInt().toString() else qty.toString()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Same Item Found") },
        text = {
            Text(
                buildAnnotatedString {
                    append("Previous Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("${formatQty(existingQty)}\n") }
                    append("New Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("${formatQty(newQty)}\n") }
                    append("Final Qty: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("${formatQty(existingQty + newQty)}\n") }
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
