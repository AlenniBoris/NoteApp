package com.example.noteapplication.presentation.home.views

import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FilePicker(
    onFilePicked: (String, String) -> Unit
){

    val context = LocalContext.current

    val pickerFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                val fileName = context.contentResolver.query(
                    uri, null, null, null, null
                )?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(nameIndex)
                }

                onFilePicked(uri.toString(), fileName ?: "Unknown")
            }
        }
    )

    IconButton(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .background(Color.Blue.copy(alpha = 0.3f), CircleShape),
        onClick = {
            pickerFileLauncher.launch(arrayOf("*/*"))
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add btn"
        )
    }

}