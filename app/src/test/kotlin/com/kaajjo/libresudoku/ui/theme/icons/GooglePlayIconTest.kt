package com.kaajjo.libresudoku.ui.theme.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPath
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GooglePlayIconTest {

    @Test
    fun googlePlayIcon_usesSquareViewportAndAllPaths() {
        val vector = Icons.Filled.GooglePlay
        val paths = vectorPaths(vector)

        assertEquals(512f, vector.viewportWidth)
        assertEquals(512f, vector.viewportHeight)
        assertEquals(4, paths.size)
        assertTrue(paths.all { it.pathData.isNotEmpty() })
    }

    private fun vectorPaths(vector: ImageVector): List<VectorPath> {
        val childrenField = vector.root.javaClass.getDeclaredField("children")
        childrenField.isAccessible = true
        val children = childrenField.get(vector.root) as List<*>
        return children.filterIsInstance<VectorPath>()
    }
}
