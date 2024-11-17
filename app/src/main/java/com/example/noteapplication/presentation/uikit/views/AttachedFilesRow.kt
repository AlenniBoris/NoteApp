package com.example.noteapplication.presentation.uikit.views

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.presentation.home.views.FilePicker

@Composable
fun AttachedFilesRow(
    attachedFilesList: List<AttachedFile>,
    onDetachAction: (AttachedFile) -> Unit = {},
    onAttachAction: (String, String) -> Unit = {_,_ ->},
    isInRefactoringMode: Boolean = false
){

    Row(
        modifier = Modifier
            .padding(top = 30.dp)
            .height(110.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(15)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow (
            modifier = Modifier
                .padding(start = 3.dp)
                .weight(1f)
        ){
            items(attachedFilesList) { attachedFile ->
                FileCard(
                    attachedFile = attachedFile,
                    onDetachBtnClicked = { onDetachAction(attachedFile) },
                    isInRefactoringMode = isInRefactoringMode
                )
            }
        }

        if (isInRefactoringMode) {
            FilePicker(
                onFilePicked = onAttachAction
            )
        }
    }
}