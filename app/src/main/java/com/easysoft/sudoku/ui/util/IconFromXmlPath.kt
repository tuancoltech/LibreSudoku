package com.easysoft.sudoku.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class IconPathData(
    val pathStr: String,
    val fillAlpha: Float = 1f,
    val strokeAlpha: Float = 1f,
)

fun iconFromXmlPath(
    pathStr: String,
    viewportWidth: Float = 24f,
    viewportHeight: Float = 24f,
    defaultWidth: Dp = 24.dp,
    defaultHeight: Dp = 24.dp,
    fillColor: Color = Color.White,
): ImageVector {
    return iconFromXmlPaths(
        paths = listOf(IconPathData(pathStr = pathStr)),
        viewportWidth = viewportWidth,
        viewportHeight = viewportHeight,
        defaultWidth = defaultWidth,
        defaultHeight = defaultHeight,
        fillColor = fillColor,
    )
}

fun iconFromXmlPaths(
    paths: List<IconPathData>,
    viewportWidth: Float = 24f,
    viewportHeight: Float = 24f,
    defaultWidth: Dp = 24.dp,
    defaultHeight: Dp = 24.dp,
    fillColor: Color = Color.White,
): ImageVector {
    val fillBrush = SolidColor(fillColor)
    val strokeBrush = SolidColor(fillColor)

    return ImageVector.Builder(
        defaultWidth = defaultWidth,
        defaultHeight = defaultHeight,
        viewportWidth = viewportWidth,
        viewportHeight = viewportHeight,
    ).run {
        paths.forEach { path ->
            addPath(
                pathData = addPathNodes(path.pathStr),
                name = "",
                fill = fillBrush,
                stroke = strokeBrush,
                fillAlpha = path.fillAlpha,
                strokeAlpha = path.strokeAlpha,
            )
        }
        build()
    }
}
