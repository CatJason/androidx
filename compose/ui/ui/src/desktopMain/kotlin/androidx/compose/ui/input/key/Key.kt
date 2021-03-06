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

package androidx.compose.ui.input.key

import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.KEY_LOCATION_LEFT
import java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD
import java.awt.event.KeyEvent.KEY_LOCATION_RIGHT
import java.awt.event.KeyEvent.KEY_LOCATION_STANDARD
import androidx.compose.ui.util.unpackInt1

// TODO(demin): implement most of key codes

/**
 * Actual implementation of [Key] for Desktop.
 *
 * @param keyCode an integer code representing the key pressed. Note: This keycode can be used to
 * uniquely identify a hardware key. It is different from the native keycode.
 */
actual inline class Key(val keyCode: Long) {
    actual companion object {
        actual val Unknown = Key(KeyEvent.VK_UNDEFINED)
        actual val Home = Key(KeyEvent.VK_HOME)
        actual val Help = Key(KeyEvent.VK_HELP)
        actual val DPadUp = Key(KeyEvent.VK_KP_UP)
        actual val DPadDown = Key(KeyEvent.VK_KP_DOWN)
        actual val DPadLeft = Key(KeyEvent.VK_KP_LEFT)
        actual val DPadRight = Key(KeyEvent.VK_KP_RIGHT)
        actual val Zero = Key(KeyEvent.VK_0)
        actual val One = Key(KeyEvent.VK_1)
        actual val Two = Key(KeyEvent.VK_2)
        actual val Three = Key(KeyEvent.VK_3)
        actual val Four = Key(KeyEvent.VK_4)
        actual val Five = Key(KeyEvent.VK_5)
        actual val Six = Key(KeyEvent.VK_6)
        actual val Seven = Key(KeyEvent.VK_7)
        actual val Eight = Key(KeyEvent.VK_8)
        actual val Nine = Key(KeyEvent.VK_9)
        actual val Plus = Key(KeyEvent.VK_PLUS)
        actual val Minus = Key(KeyEvent.VK_MINUS)
        actual val Multiply = Key(KeyEvent.VK_MULTIPLY)
        actual val Equals = Key(KeyEvent.VK_EQUALS)
        actual val A = Key(KeyEvent.VK_A)
        actual val B = Key(KeyEvent.VK_B)
        actual val C = Key(KeyEvent.VK_C)
        actual val D = Key(KeyEvent.VK_D)
        actual val E = Key(KeyEvent.VK_E)
        actual val F = Key(KeyEvent.VK_F)
        actual val G = Key(KeyEvent.VK_G)
        actual val H = Key(KeyEvent.VK_H)
        actual val I = Key(KeyEvent.VK_I)
        actual val J = Key(KeyEvent.VK_J)
        actual val K = Key(KeyEvent.VK_K)
        actual val L = Key(KeyEvent.VK_L)
        actual val M = Key(KeyEvent.VK_M)
        actual val N = Key(KeyEvent.VK_N)
        actual val O = Key(KeyEvent.VK_O)
        actual val P = Key(KeyEvent.VK_P)
        actual val Q = Key(KeyEvent.VK_Q)
        actual val R = Key(KeyEvent.VK_R)
        actual val S = Key(KeyEvent.VK_S)
        actual val T = Key(KeyEvent.VK_T)
        actual val U = Key(KeyEvent.VK_U)
        actual val V = Key(KeyEvent.VK_V)
        actual val W = Key(KeyEvent.VK_W)
        actual val X = Key(KeyEvent.VK_X)
        actual val Y = Key(KeyEvent.VK_Y)
        actual val Z = Key(KeyEvent.VK_Z)
        actual val Comma = Key(KeyEvent.VK_COMMA)
        actual val Period = Key(KeyEvent.VK_PERIOD)
        actual val AltLeft = Key(KeyEvent.VK_ALT, KEY_LOCATION_LEFT)
        actual val AltRight = Key(KeyEvent.VK_ALT, KEY_LOCATION_RIGHT)
        actual val ShiftLeft = Key(KeyEvent.VK_SHIFT, KEY_LOCATION_LEFT)
        actual val ShiftRight = Key(KeyEvent.VK_SHIFT, KEY_LOCATION_RIGHT)
        actual val Tab = Key(KeyEvent.VK_TAB)
        actual val Spacebar = Key(KeyEvent.VK_SPACE)
        actual val Enter = Key(KeyEvent.VK_ENTER)
        actual val Backspace = Key(KeyEvent.VK_BACK_SPACE)
        actual val Delete = Key(KeyEvent.VK_DELETE)
        actual val Escape = Key(KeyEvent.VK_ESCAPE)
        actual val CtrlLeft = Key(KeyEvent.VK_CONTROL, KEY_LOCATION_LEFT)
        actual val CtrlRight = Key(KeyEvent.VK_CONTROL, KEY_LOCATION_RIGHT)
        actual val CapsLock = Key(KeyEvent.VK_CAPS_LOCK)
        actual val ScrollLock = Key(KeyEvent.VK_SCROLL_LOCK)
        actual val MetaLeft = Key(KeyEvent.VK_META, KEY_LOCATION_LEFT)
        actual val MetaRight = Key(KeyEvent.VK_META, KEY_LOCATION_RIGHT)
        actual val PrintScreen = Key(KeyEvent.VK_PRINTSCREEN)
        actual val Grave = Key(KeyEvent.VK_BACK_QUOTE)
        actual val LeftBracket = Key(KeyEvent.VK_OPEN_BRACKET)
        actual val RightBracket = Key(KeyEvent.VK_CLOSE_BRACKET)
        actual val Slash = Key(KeyEvent.VK_SLASH)
        actual val Backslash = Key(KeyEvent.VK_BACK_SLASH)
        actual val Semicolon = Key(KeyEvent.VK_SEMICOLON)
        actual val Apostrophe = Key(KeyEvent.VK_QUOTE)
        actual val At = Key(KeyEvent.VK_AT)
        actual val PageUp = Key(KeyEvent.VK_PAGE_UP)
        actual val PageDown = Key(KeyEvent.VK_PAGE_UP)
        actual val F1 = Key(KeyEvent.VK_F1)
        actual val F2 = Key(KeyEvent.VK_F2)
        actual val F3 = Key(KeyEvent.VK_F3)
        actual val F4 = Key(KeyEvent.VK_F4)
        actual val F5 = Key(KeyEvent.VK_F5)
        actual val F6 = Key(KeyEvent.VK_F6)
        actual val F7 = Key(KeyEvent.VK_F7)
        actual val F8 = Key(KeyEvent.VK_F8)
        actual val F9 = Key(KeyEvent.VK_F9)
        actual val F10 = Key(KeyEvent.VK_F10)
        actual val F11 = Key(KeyEvent.VK_F11)
        actual val F12 = Key(KeyEvent.VK_F12)
        actual val NumLock = Key(KeyEvent.VK_NUM_LOCK, KEY_LOCATION_NUMPAD)
        actual val NumPad0 = Key(KeyEvent.VK_NUMPAD0, KEY_LOCATION_NUMPAD)
        actual val NumPad1 = Key(KeyEvent.VK_NUMPAD1, KEY_LOCATION_NUMPAD)
        actual val NumPad2 = Key(KeyEvent.VK_NUMPAD2, KEY_LOCATION_NUMPAD)
        actual val NumPad3 = Key(KeyEvent.VK_NUMPAD3, KEY_LOCATION_NUMPAD)
        actual val NumPad4 = Key(KeyEvent.VK_NUMPAD4, KEY_LOCATION_NUMPAD)
        actual val NumPad5 = Key(KeyEvent.VK_NUMPAD5, KEY_LOCATION_NUMPAD)
        actual val NumPad6 = Key(KeyEvent.VK_NUMPAD6, KEY_LOCATION_NUMPAD)
        actual val NumPad7 = Key(KeyEvent.VK_NUMPAD7, KEY_LOCATION_NUMPAD)
        actual val NumPad8 = Key(KeyEvent.VK_NUMPAD8, KEY_LOCATION_NUMPAD)
        actual val NumPad9 = Key(KeyEvent.VK_NUMPAD9, KEY_LOCATION_NUMPAD)
        actual val NumPadDivide = Key(KeyEvent.VK_DIVIDE, KEY_LOCATION_NUMPAD)
        actual val NumPadMultiply = Key(KeyEvent.VK_MULTIPLY, KEY_LOCATION_NUMPAD)
        actual val NumPadSubtract = Key(KeyEvent.VK_SUBTRACT, KEY_LOCATION_NUMPAD)
        actual val NumPadAdd = Key(KeyEvent.VK_ADD, KEY_LOCATION_NUMPAD)
        actual val NumPadDot = Key(KeyEvent.VK_PERIOD, KEY_LOCATION_NUMPAD)
        actual val NumPadComma = Key(KeyEvent.VK_COMMA, KEY_LOCATION_NUMPAD)
        actual val NumPadEnter = Key(KeyEvent.VK_ENTER, KEY_LOCATION_NUMPAD)
        actual val NumPadEquals = Key(KeyEvent.VK_EQUALS, KEY_LOCATION_NUMPAD)
        actual val NumPadLeftParenthesis = Key(KeyEvent.VK_LEFT_PARENTHESIS, KEY_LOCATION_NUMPAD)
        actual val NumPadRightParenthesis = Key(KeyEvent.VK_RIGHT_PARENTHESIS, KEY_LOCATION_NUMPAD)

        // Unsupported Keys. These keys will never be sent by the desktop. However we need unique
        // keycodes so that these constants can be used in a when statement without a warning.
        actual val SoftLeft = Key(-1000000001)
        actual val SoftRight = Key(-1000000002)
        actual val Back = Key(-1000000003)
        actual val NavigatePrevious = Key(-1000000004)
        actual val NavigateNext = Key(-1000000005)
        actual val NavigateIn = Key(-1000000006)
        actual val NavigateOut = Key(-1000000007)
        actual val SystemNavigationUp = Key(-1000000008)
        actual val SystemNavigationDown = Key(-1000000009)
        actual val SystemNavigationLeft = Key(-1000000010)
        actual val SystemNavigationRight = Key(-1000000011)
        actual val Call = Key(-1000000012)
        actual val EndCall = Key(-1000000013)
        actual val DPadCenter = Key(-1000000014)
        actual val DPadUpLeft = Key(-1000000015)
        actual val DPadDownLeft = Key(-1000000016)
        actual val DPadUpRight = Key(-1000000017)
        actual val DPadDownRight = Key(-1000000018)
        actual val VolumeUp = Key(-1000000019)
        actual val VolumeDown = Key(-1000000020)
        actual val Power = Key(-1000000021)
        actual val Camera = Key(-1000000022)
        actual val Clear = Key(-1000000023)
        actual val Pound = Key(-1000000024)
        actual val Symbol = Key(-1000000025)
        actual val Browser = Key(-1000000026)
        actual val Envelope = Key(-1000000027)
        actual val Function = Key(-1000000028)
        actual val Break = Key(-1000000029)
        actual val MoveHome = Key(-1000000030)
        actual val MoveEnd = Key(-1000000031)
        actual val Insert = Key(-1000000032)
        actual val Cut = Key(-1000000033)
        actual val Copy = Key(-1000000034)
        actual val Paste = Key(-1000000035)
        actual val Number = Key(-1000000036)
        actual val HeadsetHook = Key(-1000000037)
        actual val Focus = Key(-1000000038)
        actual val Menu = Key(-1000000039)
        actual val Notification = Key(-1000000040)
        actual val Search = Key(-1000000041)
        actual val PictureSymbols = Key(-1000000042)
        actual val SwitchCharset = Key(-1000000043)
        actual val ButtonA = Key(-1000000044)
        actual val ButtonB = Key(-1000000045)
        actual val ButtonC = Key(-1000000046)
        actual val ButtonX = Key(-1000000047)
        actual val ButtonY = Key(-1000000048)
        actual val ButtonZ = Key(-1000000049)
        actual val ButtonL1 = Key(-1000000050)
        actual val ButtonR1 = Key(-1000000051)
        actual val ButtonL2 = Key(-1000000052)
        actual val ButtonR2 = Key(-1000000053)
        actual val ButtonThumbLeft = Key(-1000000054)
        actual val ButtonThumbRight = Key(-1000000055)
        actual val ButtonStart = Key(-1000000056)
        actual val ButtonSelect = Key(-1000000057)
        actual val ButtonMode = Key(-1000000058)
        actual val Button1 = Key(-1000000059)
        actual val Button2 = Key(-1000000060)
        actual val Button3 = Key(-1000000061)
        actual val Button4 = Key(-1000000062)
        actual val Button5 = Key(-1000000063)
        actual val Button6 = Key(-1000000064)
        actual val Button7 = Key(-1000000065)
        actual val Button8 = Key(-1000000066)
        actual val Button9 = Key(-1000000067)
        actual val Button10 = Key(-1000000068)
        actual val Button11 = Key(-1000000069)
        actual val Button12 = Key(-1000000070)
        actual val Button13 = Key(-1000000071)
        actual val Button14 = Key(-1000000072)
        actual val Button15 = Key(-1000000073)
        actual val Button16 = Key(-1000000074)
        actual val Forward = Key(-1000000075)
        actual val MediaPlay = Key(-1000000076)
        actual val MediaPause = Key(-1000000077)
        actual val MediaPlayPause = Key(-1000000078)
        actual val MediaStop = Key(-1000000079)
        actual val MediaRecord = Key(-1000000080)
        actual val MediaNext = Key(-1000000081)
        actual val MediaPrevious = Key(-1000000082)
        actual val MediaRewind = Key(-1000000083)
        actual val MediaFastForward = Key(-1000000084)
        actual val MediaClose = Key(-1000000085)
        actual val MediaAudioTrack = Key(-1000000086)
        actual val MediaEject = Key(-1000000087)
        actual val MediaTopMenu = Key(-1000000088)
        actual val MediaSkipForward = Key(-1000000089)
        actual val MediaSkipBackward = Key(-1000000090)
        actual val MediaStepForward = Key(-1000000091)
        actual val MediaStepBackward = Key(-1000000092)
        actual val MicrophoneMute = Key(-1000000093)
        actual val VolumeMute = Key(-1000000094)
        actual val Info = Key(-1000000095)
        actual val ChannelUp = Key(-1000000096)
        actual val ChannelDown = Key(-1000000097)
        actual val ZoomIn = Key(-1000000098)
        actual val ZoomOut = Key(-1000000099)
        actual val Tv = Key(-1000000100)
        actual val Window = Key(-1000000101)
        actual val Guide = Key(-1000000102)
        actual val Dvr = Key(-1000000103)
        actual val Bookmark = Key(-1000000104)
        actual val Captions = Key(-1000000105)
        actual val Settings = Key(-1000000106)
        actual val TvPower = Key(-1000000107)
        actual val TvInput = Key(-1000000108)
        actual val SetTopBoxPower = Key(-1000000109)
        actual val SetTopBoxInput = Key(-1000000110)
        actual val AvReceiverPower = Key(-1000000111)
        actual val AvReceiverInput = Key(-1000000112)
        actual val ProgramRed = Key(-1000000113)
        actual val ProgramGreen = Key(-1000000114)
        actual val ProgramYellow = Key(-1000000115)
        actual val ProgramBlue = Key(-1000000116)
        actual val AppSwitch = Key(-1000000117)
        actual val LanguageSwitch = Key(-1000000118)
        actual val MannerMode = Key(-1000000119)
        actual val Toggle2D3D = Key(-1000000120)
        actual val Contacts = Key(-1000000121)
        actual val Calendar = Key(-1000000122)
        actual val Music = Key(-1000000123)
        actual val Calculator = Key(-1000000124)
        actual val ZenkakuHankaru = Key(-1000000125)
        actual val Eisu = Key(-1000000126)
        actual val Muhenkan = Key(-1000000127)
        actual val Henkan = Key(-1000000128)
        actual val KatakanaHiragana = Key(-1000000129)
        actual val Yen = Key(-1000000130)
        actual val Ro = Key(-1000000131)
        actual val Kana = Key(-1000000132)
        actual val Assist = Key(-1000000133)
        actual val BrightnessDown = Key(-1000000134)
        actual val BrightnessUp = Key(-1000000135)
        actual val Sleep = Key(-1000000136)
        actual val WakeUp = Key(-1000000137)
        actual val SoftSleep = Key(-1000000138)
        actual val Pairing = Key(-1000000139)
        actual val LastChannel = Key(-1000000140)
        actual val TvDataService = Key(-1000000141)
        actual val VoiceAssist = Key(-1000000142)
        actual val TvRadioService = Key(-1000000143)
        actual val TvTeletext = Key(-1000000144)
        actual val TvNumberEntry = Key(-1000000145)
        actual val TvTerrestrialAnalog = Key(-1000000146)
        actual val TvTerrestrialDigital = Key(-1000000147)
        actual val TvSatellite = Key(-1000000148)
        actual val TvSatelliteBs = Key(-1000000149)
        actual val TvSatelliteCs = Key(-1000000150)
        actual val TvSatelliteService = Key(-1000000151)
        actual val TvNetwork = Key(-1000000152)
        actual val TvAntennaCable = Key(-1000000153)
        actual val TvInputHdmi1 = Key(-1000000154)
        actual val TvInputHdmi2 = Key(-1000000155)
        actual val TvInputHdmi3 = Key(-1000000156)
        actual val TvInputHdmi4 = Key(-1000000157)
        actual val TvInputComposite1 = Key(-1000000158)
        actual val TvInputComposite2 = Key(-1000000159)
        actual val TvInputComponent1 = Key(-1000000160)
        actual val TvInputComponent2 = Key(-1000000161)
        actual val TvInputVga1 = Key(-1000000162)
        actual val TvAudioDescription = Key(-1000000163)
        actual val TvAudioDescriptionMixingVolumeUp = Key(-1000000164)
        actual val TvAudioDescriptionMixingVolumeDown = Key(-1000000165)
        actual val TvZoomMode = Key(-1000000166)
        actual val TvContentsMenu = Key(-1000000167)
        actual val TvMediaContextMenu = Key(-1000000168)
        actual val TvTimerProgramming = Key(-1000000169)
        actual val StemPrimary = Key(-1000000170)
        actual val Stem1 = Key(-1000000171)
        actual val Stem2 = Key(-1000000172)
        actual val Stem3 = Key(-1000000173)
        actual val AllApps = Key(-1000000174)
        actual val Refresh = Key(-1000000175)
        actual val ThumbsUp = Key(-1000000176)
        actual val ThumbsDown = Key(-1000000177)
        actual val ProfileSwitch = Key(-1000000178)
    }

    actual override fun toString(): String {
        return "Key: ${KeyEvent.getKeyText(nativeKeyCode)}"
    }
}

/**
 * Creates instance of [Key].
 *
 * @param nativeKeyCode represents this key as defined in [java.awt.event.KeyEvent]
 * @param nativeKeyLocation represents the location of key as defined in [java.awt.event.KeyEvent]
 */
fun Key(nativeKeyCode: Int, nativeKeyLocation: Int = KEY_LOCATION_STANDARD): Key {
    // First 32 bits are for keycode.
    val keyCode = nativeKeyCode.toLong().shl(32)

    // Next 3 bits are for location.
    val location = (nativeKeyLocation.toLong() and 0x7).shl(29)

    return Key(keyCode or location)
}

/**
 * The native keycode corresponding to this [Key].
 */
val Key.nativeKeyCode: Int
    get() = unpackInt1(keyCode)

/**
 * The native location corresponding to this [Key].
 */
val Key.nativeKeyLocation: Int
    get() = (keyCode and 0xFFFFFFFF).shr(29).toInt()
