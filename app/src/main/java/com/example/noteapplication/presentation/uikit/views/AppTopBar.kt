package com.example.noteapplication.presentation.uikit.views

import android.widget.ImageButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTopBar(
    needsAddButton: Boolean,
    onAddButtonClicked: () -> Unit,

    needsSortButton: Boolean,
    onSortButtonClicked: () -> Unit,

    needsEditButton: Boolean,
    onEditButtonClicked: () -> Unit,

    needsAcceptButton: Boolean,
    onAcceptButtonClicked: () -> Unit,

    needsDeclineButton: Boolean,
    onDeclineButtonClicked: () -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ){

        if (needsAddButton) {
            IconButton(
                onClick = onAddButtonClicked
            ){
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Icon Add"
                )
            }
        }

        if (needsSortButton) {
            IconButton(
                onClick = onSortButtonClicked
            ){
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Icon sort"
                )
            }
        }

        if (needsEditButton) {
            IconButton(
                onClick = onEditButtonClicked
            ){
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Icon edit"
                )
            }
        }

        if (needsAcceptButton) {
            IconButton(
                onClick = onAcceptButtonClicked
            ){
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Icon accept"
                )
            }
        }

        if (needsDeclineButton) {
            IconButton(
                onClick = onDeclineButtonClicked
            ){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Icon decline"
                )
            }
        }

    }

}