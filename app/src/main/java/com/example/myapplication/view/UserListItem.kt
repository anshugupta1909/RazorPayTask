package com.example.myapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.UserResponse


@Composable
fun UserListItem(userResponse: UserResponse,
                 onEdit: (UserResponse) -> Unit,
                 onDelete: (UserResponse) -> Unit,
                 onToggleComplete: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = 20.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Id: ${userResponse.id}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Title: ${userResponse.title}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(5.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
                , horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "Is Task Completed",
                    modifier = Modifier.wrapContentSize(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
                Checkbox(
                    checked = userResponse.isCompleted,
                    onCheckedChange = { onToggleComplete() } // ✅ Toggle task completion
                )
            IconButton(onClick = { onEdit(userResponse) }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = { onDelete(userResponse) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
            }


           Text(
                text = "Status: ${userResponse.isCompleted}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}