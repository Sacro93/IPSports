package com.example.ipsports.View.Event.ReusableEvent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ipsports.ui.theme.IpSportsTheme
import java.util.Calendar
import android.app.DatePickerDialog
import android.app.TimePickerDialog


@Composable
fun EventInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    fieldType: FieldType = FieldType.Text, // Indica el tipo de campo (Texto, Fecha, Hora)
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()



    // Acciones para DatePicker y TimePicker
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            calendar.set(year, month, day)
            val formattedDate = "${day.toString().padStart(2, '0')}/${(month + 1).toString().padStart(2, '0')}/$year"
            onValueChange(formattedDate) // Actualiza el valor al seleccionar fecha
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            val formattedTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
            onValueChange(formattedTime) // Actualiza el valor al seleccionar hora
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (fieldType == FieldType.Text) onValueChange(it) // Solo texto actualizable directamente
        },
        label = { Text(text = label) },
        leadingIcon = leadingIcon,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = fieldType != FieldType.Text) {
                // Abrir DatePicker o TimePicker según el tipo
                when (fieldType) {
                    FieldType.Date -> datePickerDialog.show()
                    FieldType.Time -> timePickerDialog.show()
                    FieldType.Text -> {} // Nada, comportamiento normal
                }
            },
        readOnly = fieldType != FieldType.Text // Hacerlo no editable si no es texto
    )
}


@Preview(showBackground = true)
@Composable
fun EventInputFieldPreview() {
    IpSportsTheme {
        EventInputField(
            label = "Fecha y Hora",
            value = "28/01/2025",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Fecha") }
        )
    }
}
