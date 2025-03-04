package com.example.ipsports.View.Event.ReusableEvent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun EventInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    fieldType: FieldType = FieldType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (fieldType == FieldType.Text) onValueChange(it) },
        label = { Text(text = label) },
        leadingIcon = leadingIcon,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = fieldType != FieldType.Text) { onClick?.invoke() },
        readOnly = fieldType != FieldType.Text
    )
}



