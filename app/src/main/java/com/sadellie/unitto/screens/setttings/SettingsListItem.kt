package com.sadellie.unitto.screens.setttings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Basic list item for settings screen. By default only has label and support text, clickable.
 * This component can be easily modified if you provide additional component to it,
 * for example a switch or a checkbox
 *
 * @param label Main text
 * @param supportText Text that is located below label
 * @param onClick Action to perform when user clicks on this component (whole component is clickable)
 * @param content Additional composable: buttons, switches, checkboxes or something else
 */
@Composable
private fun BasicSettingsListItem(
    label: String,
    supportText: String? = null,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            Modifier
                .padding(horizontal = 0.dp)
                .weight(1f)  // This makes additional composable to be seen
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = label
            )
            supportText?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        content()
    }
}

/**
 * Represents one item in list on Settings screen
 *
 * @param label Main text
 * @param supportText Text that is located below label
 * @param onClick Action to perform when user clicks on this component (whole component is clickable)
 */
@Composable
fun SettingsListItem(
    label: String,
    supportText: String? = null,
    onClick: () -> Unit,
) = BasicSettingsListItem(label, supportText, onClick)

/**
 * Represents one item in list on Settings screen
 *
 * @param label Main text
 * @param supportText Text that is located below label
 * @param switchState Current switch state
 * @param onSwitchChange Action to perform when user clicks on this component or just switch
 */
@Composable
fun SettingsListItem(
    label: String,
    supportText: String? = null,
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) = BasicSettingsListItem(label, supportText, { onSwitchChange(switchState) }) {
    Switch(checked = switchState, onCheckedChange = { onSwitchChange(!it) })
}
