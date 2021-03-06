/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.ui.focus

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.node.ModifiedFocusNode
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.NoInspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.focus.FocusState.Active
import androidx.compose.ui.focus.FocusState.Inactive
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.input.key.Key.Companion.DPadRight
import androidx.compose.ui.input.key.Key.Companion.DPadLeft
import androidx.compose.ui.input.key.Key.Companion.DPadUp
import androidx.compose.ui.input.key.Key.Companion.DPadDown
import androidx.compose.ui.focus.FocusDirection.Next
import androidx.compose.ui.focus.FocusDirection.Previous
import androidx.compose.ui.focus.FocusDirection.Left
import androidx.compose.ui.focus.FocusDirection.Right
import androidx.compose.ui.focus.FocusDirection.Up
import androidx.compose.ui.focus.FocusDirection.Down
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType.KeyDown
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type

/**
 * A [Modifier.Element] that wraps makes the modifiers on the right into a Focusable. Use a
 * different instance of [FocusModifier] for each focusable component.
 */
internal class FocusModifier(
    initialFocus: FocusState,
    // TODO(b/172265016): Make this a required parameter and remove the default value.
    //  Set this value in AndroidComposeView, and other places where we create a focus modifier
    //  using this internal constructor.
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo
) : Modifier.Element, InspectorValueInfo(inspectorInfo) {

    var focusState: FocusState = initialFocus
        set(value) {
            field = value
            focusNode.wrappedBy?.propagateFocusEvent(value)
        }

    var focusedChild: ModifiedFocusNode? = null

    lateinit var focusNode: ModifiedFocusNode
}

/**
 * Add this modifier to a component to make it focusable.
 */
fun Modifier.focusModifier(): Modifier = composed(debugInspectorInfo { name = "focusModifier" }) {
    val focusModifier = remember { FocusModifier(Inactive) }
    focusModifier.onKeyEvent {
        val direction = getFocusDirection(it)
        if (direction != null && it.type == KeyDown && focusModifier.focusState == Active) {
            focusModifier.focusNode.moveFocus(direction)
            true
        } else {
            false
        }
    }
}

private fun getFocusDirection(keyEvent: KeyEvent) = when (keyEvent.key) {
    Tab -> if (keyEvent.isShiftPressed) Previous else Next
    DPadRight -> Right
    DPadLeft -> Left
    DPadUp -> Up
    DPadDown -> Down
    else -> null
}
