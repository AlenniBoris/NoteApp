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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.noteapplication.R

@Composable
fun ActionsRow(
    actionIconSize: Dp,
    onDelete: () -> Unit,
    onPin: () -> Unit,
    noteISPinned: Boolean
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
            onClick = onPin,
            modifier = Modifier.size(actionIconSize)
        ) {
            Icon(
                painter = if(noteISPinned)
                    painterResource(R.drawable.baseline_lock_outline_24)
                else painterResource(R.drawable.baseline_lock_open_24),
                contentDescription = "Icon pin"
            )
        }
    }
}