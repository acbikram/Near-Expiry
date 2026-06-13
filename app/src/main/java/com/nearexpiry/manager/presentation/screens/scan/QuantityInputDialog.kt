package com.nearexpiry.manager.presentation.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun QuantityInputDialog(
    onQuantityConfirmed: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    // Start empty so the field shows just a blinking cursor, matching the
    // scan flow's other "ready to type" inputs.
    var quantityText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Auto-focus the field and pop the number pad as soon as the dialog appears.
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Quantity") },
        text = {
            Column {
                OutlinedTextField(
                    value = quantityText,
                    onValueChange = {
                        quantityText = it.filter { char -> char.isDigit() }
                        error = null
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Quantity") },
                    isError = error != null,
                    supportingText = { error?.let { Text(it) } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val qty = quantityText.toIntOrNull()
                    if (qty != null && qty in 1..99999) {
                        onQuantityConfirmed(qty)
                    } else {
                        error = "Quantity must be between 1 and 99999"
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
