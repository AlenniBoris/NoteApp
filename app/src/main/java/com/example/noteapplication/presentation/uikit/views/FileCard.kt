package com.example.noteapplication.presentation.uikit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.noteapplication.domain.model.AttachedFile

@Composable
fun FileCard(
    attachedFile: AttachedFile,
    onDetachBtnClicked: () -> Unit,
    isInRefactoringMode: Boolean,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .width(90.dp)
            .fillMaxHeight()
            .background(Color.Blue.copy(alpha = 0.3f), RoundedCornerShape(15))
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = attachedFile.fileName,
            color = Color.White
        )
        if (isInRefactoringMode){
            IconButton(
                modifier = Modifier.background(Color.Gray.copy(alpha = 0.6f), CircleShape)
                    .align(Alignment.TopEnd),
                onClick = onDetachBtnClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Icon delete file"
                )
            }
        }
    }
}