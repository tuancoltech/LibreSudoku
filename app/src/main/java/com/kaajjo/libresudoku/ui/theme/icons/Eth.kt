package com.kaajjo.libresudoku.ui.theme.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.kaajjo.libresudoku.ui.util.IconPathData
import com.kaajjo.libresudoku.ui.util.iconFromXmlPaths

val Icons.Filled.Eth by lazy {
    ethIcon()
}

private fun ethIcon(): ImageVector {
    return iconFromXmlPaths(
        paths = listOf(
            IconPathData(
                pathStr = "m269.9386,199.6907 l-165.7335,75.3446 165.7335,98.0032 165.7949,-98.0032z",
                fillAlpha = 0.6f,
                strokeAlpha = 0.6f,
            ),
            IconPathData(
                pathStr = "m104.2665,274.9738 l165.7335,98.0032v-372.9816z",
                fillAlpha = 0.45f,
                strokeAlpha = 0.45f,
            ),
            IconPathData(
                pathStr = "m270,0v372.9816l165.7335,-98.0032z",
                fillAlpha = 0.8f,
                strokeAlpha = 0.8f,
            ),
            IconPathData(
                pathStr = "m104.2051,306.4135 l165.7335,233.5263v-135.6436z",
                fillAlpha = 0.45f,
                strokeAlpha = 0.45f,
            ),
            IconPathData(
                pathStr = "m269.9386,404.3552v135.6436l165.8563,-233.5263z",
                fillAlpha = 0.8f,
                strokeAlpha = 0.8f,
            ),
        ),
        viewportWidth = 540f,
        viewportHeight = 540f,
    )
}
