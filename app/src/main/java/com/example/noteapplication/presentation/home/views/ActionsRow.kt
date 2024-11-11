package com.example.noteapplication.presentation.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ActionsRow(
    actionIconSize: Dp,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onPin: () -> Unit
){
    Row{
        IconButton(
            onClick = onDelete,
            modifier = Modifier.size(actionIconSize),
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Icon delete"
            )
        }

        IconButton(
            onClick = onEdit,
            modifier = Modifier.size(actionIconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Icon edit"
            )
        }

        IconButton(
            onClick = onPin,
            modifier = Modifier.size(actionIconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Icon pin"
            )
        }
    }
}