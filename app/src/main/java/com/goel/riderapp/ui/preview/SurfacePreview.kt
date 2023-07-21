package com.goel.riderapp.ui.preview

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.goel.riderapp.ui.theme.RiderAppTheme

@Preview(showBackground = true)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class SurfacePreview

@Composable
fun PreviewSurface(content: @Composable () -> Unit) = RiderAppTheme { Surface { content() } }
