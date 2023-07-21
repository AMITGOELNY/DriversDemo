package com.goel.riderapp.ui.route

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.goel.riderapp.ui.preview.PreviewSurface
import com.goel.riderapp.ui.preview.SurfacePreview
import com.goel.riderapp.ui.route.viewmodel.RouteUI

@Composable
fun RoutesCard(route: RouteUI) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
            Text(text = route.name, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFF437E23), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = route.type.first().uppercase(), color = Color(0xFF437E23))
            }
        }
    }
}

@SurfacePreview
@Composable
private fun RoutesCardPreview() = PreviewSurface {
    RoutesCard(route = RouteUI("id", "R", "North Side Residential Route"))
}
