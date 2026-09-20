package pro.jayeshseth.mugen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import pro.jayeshseth.mugen.locals.LocalMugenColors
import pro.jayeshseth.mugen.locals.LocalMugenDefaults

@Immutable
data class MugenChipDefaults(
    val contentPadding: PaddingValues,
    val shape: CornerBasedShape,
    val renderer: MugenChipRenderer? = null,
)

val LocalMugenChipDefaults = compositionLocalOf<MugenChipDefaults?> { null }

@Stable
interface MugenChipRenderer {
    @Composable
    fun Render(
        defaults: MugenChipDefaults,
        modifier: Modifier,
        onClick: () -> Unit,
        content: @Composable RowScope.() -> Unit,
    )
}

object PlainChipRenderer : MugenChipRenderer {
    @Composable
    override fun Render(
        defaults: MugenChipDefaults,
        modifier: Modifier,
        onClick: () -> Unit,
        content: @Composable RowScope.() -> Unit,
    ) {
        val colors = LocalMugenColors.current
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(defaults.shape)
                .background(colors.surfaceVariant)
                .clickable(onClick = onClick)
                .padding(defaults.contentPadding),
        ) {
            CompositionLocalProvider(LocalContentColor provides colors.onSurfaceVariant) {
                Row(content = content)
            }
        }
    }
}

/**
 * Proof-of-concept standalone MugenChip component.
 * Demonstrates that new components can be created and resolve token defaults without
 * modifying [MugenLook] or any third-party Look implementations.
 */
@Composable
fun MugenChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    renderer: MugenChipRenderer? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val defaults = LocalMugenChipDefaults.current
        ?: LocalMugenDefaults.current.resolve<MugenChipDefaults>()
    val activeRenderer = renderer
        ?: defaults.renderer
        ?: PlainChipRenderer
    activeRenderer.Render(
        defaults = defaults,
        modifier = modifier,
        onClick = onClick,
        content = content,
    )
}
