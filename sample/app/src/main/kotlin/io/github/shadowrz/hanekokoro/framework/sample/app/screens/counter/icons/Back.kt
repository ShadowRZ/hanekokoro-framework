package io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Back: ImageVector
    get() {
        if (_Back != null) {
            return _Back!!
        }
        _Back = ImageVector.Builder(
            name = "Back",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(7.825f, 13f)
                lineToRelative(5.6f, 5.6f)
                lineTo(12f, 20f)
                lineToRelative(-8f, -8f)
                lineToRelative(8f, -8f)
                lineToRelative(1.425f, 1.4f)
                lineToRelative(-5.6f, 5.6f)
                horizontalLineTo(20f)
                verticalLineToRelative(2f)
                close()
            }
        }.build()

        return _Back!!
    }

@Suppress("ObjectPropertyName")
private var _Back: ImageVector? = null
