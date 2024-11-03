package com.techlads.jetsleepsounds.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Checkbox
import androidx.tv.material3.CheckboxDefaults
import androidx.tv.material3.LocalContentColor
import com.techlads.jetsleepsounds.ui.theme.JetSleepSoundsTheme

@Composable
fun JSSCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused = interactionSource.collectIsFocusedAsState()
    Checkbox(
        checked = isChecked,
        modifier = modifier.size(20.dp),
        colors = CheckboxDefaults.colors(
            checkedColor = LocalContentColor.current.copy(
                alpha = if (isFocused.value) 1f else 0.6f
            ), uncheckedColor = LocalContentColor.current.copy(
                alpha = if (isFocused.value) 1f else 0.6f
            )
        ),
        onCheckedChange = {
            onCheckedChange(it)
        },
        interactionSource = interactionSource
    )
}

@Preview
@Composable
private fun JSSCheckboxPreview() {
    JetSleepSoundsTheme {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .width(300.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            JSSCheckbox(isChecked = false, onCheckedChange = { })
            JSSCheckbox(isChecked = true, onCheckedChange = { })
        }
    }
}