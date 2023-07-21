package com.goel.riderapp.ui.driver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.goel.riderapp.ui.driver.viewModel.DriverUI
import com.goel.riderapp.ui.preview.PreviewSurface
import com.goel.riderapp.ui.preview.SurfacePreview

@Composable
fun DriversCard(driver: DriverUI, onDriverCardClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDriverCardClicked(driver.id) },
        elevation = 2.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground.copy(alpha = .1f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF241FC5)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = driver.name.first().uppercase(), color = Color.White)
            }
            Text(text = driver.name, style = MaterialTheme.typography.body1)
        }
    }
}

@SurfacePreview
@Composable
private fun DriversCardPreview() = PreviewSurface {
    DriversCard(driver = DriverUI("id", "Jeff Newbury")) {}
}
