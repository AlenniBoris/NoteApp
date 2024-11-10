package com.example.noteapplication.presentation.uikit.views

import android.widget.ImageButton
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppTopBar(
    needsBackButton: Boolean = false,
    onBackButtonClicked: () -> Unit = {},

    needsAddButton: Boolean = false,
    onAddButtonClicked: () -> Unit = {},

    needsSortButton: Boolean = false,
    onSortButtonClicked: () -> Unit = {},

    needsEditButton: Boolean = false,
    onEditButtonClicked: () -> Unit = {},

    needsAcceptButton: Boolean = false,
    onAcceptButtonClicked: () -> Unit = {},

    needsDeclineButton: Boolean = false,
    onDeclineButtonClicked: () -> Unit = {},

    needsDeleteAllButton: Boolean = false,
    onDeleteAllButtonClicked: () -> Unit = {},

    needsPriorityButtons: Boolean = false,
    onPriorityButtonClicked: (Int) -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        if (needsBackButton){
            IconButton(
                onClick = onBackButtonClicked,
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Icon Back"
                )
            }
        }

        Row {
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

            if (needsDeleteAllButton) {
                IconButton(
                    onClick = onDeleteAllButtonClicked
                ){
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Icon delete"
                    )
                }
            }
        }

        if (needsPriorityButtons){
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(Color.Red.copy(alpha = 0.55f))
                    .clickable {
                        onPriorityButtonClicked(1)
                    }
            )
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow.copy(alpha = 0.55f))
                    .clickable {
                        onPriorityButtonClicked(2)
                    }
            )
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(Color.Green.copy(alpha = 0.55f))
                    .clickable {
                        onPriorityButtonClicked(3)
                    }
            )
        }

    }

}