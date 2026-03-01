package com.easysoft.sudoku.ui.util

import androidx.compose.ui.graphics.vector.VectorPath
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IconFromXmlPathTest {

    @Test
    fun iconFromXmlPaths_buildsVectorWithAllPathsAndAlphas() {
        val vector = iconFromXmlPaths(
            paths = listOf(
                IconPathData(pathStr = "M0 0 L10 0 L10 10 Z", fillAlpha = 0.6f, strokeAlpha = 0.6f),
                IconPathData(pathStr = "M1 1 L5 5 Z", fillAlpha = 0.45f, strokeAlpha = 0.45f),
            ),
            viewportWidth = 540f,
            viewportHeight = 540f,
        )

        val vectorPaths = vectorPaths(vector)

        assertEquals(2, vectorPaths.size)
        assertEquals(540f, vector.viewportWidth)
        assertEquals(540f, vector.viewportHeight)
        assertEquals(0.6f, vectorPaths[0].fillAlpha)
        assertEquals(0.6f, vectorPaths[0].strokeAlpha)
        assertEquals(0.45f, vectorPaths[1].fillAlpha)
        assertEquals(0.45f, vectorPaths[1].strokeAlpha)
        assertTrue(vectorPaths[0].pathData.isNotEmpty())
        assertTrue(vectorPaths[1].pathData.isNotEmpty())
    }

    @Test
    fun iconFromXmlPath_delegatesToMultiPathBuilder() {
        val vector = iconFromXmlPath(
            pathStr = "M0 0 L1 1 Z",
            viewportWidth = 24f,
            viewportHeight = 24f,
        )

        val vectorPaths = vectorPaths(vector)

        assertEquals(1, vectorPaths.size)
        assertEquals(1f, vectorPaths[0].fillAlpha)
        assertEquals(1f, vectorPaths[0].strokeAlpha)
    }

    private fun vectorPaths(vector: androidx.compose.ui.graphics.vector.ImageVector): List<VectorPath> {
        val childrenField = vector.root.javaClass.getDeclaredField("children")
        childrenField.isAccessible = true
        val children = childrenField.get(vector.root) as List<*>
        return children.filterIsInstance<VectorPath>()
    }
}
