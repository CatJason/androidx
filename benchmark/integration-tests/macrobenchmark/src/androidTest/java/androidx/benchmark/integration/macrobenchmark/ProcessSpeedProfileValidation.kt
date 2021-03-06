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

package androidx.benchmark.integration.macrobenchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.MacrobenchmarkConfig
import androidx.benchmark.macro.MacrobenchmarkRule
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.test.filters.LargeTest
import androidx.test.filters.SdkSuppress
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@LargeTest
@SdkSuppress(minSdkVersion = 29)
@RunWith(Parameterized::class)
class ProcessSpeedProfileValidation(
    private val compilationMode: CompilationMode,
    private val startupMode: StartupMode
) {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun start() = benchmarkRule.measureStartupRepeated(
        MacrobenchmarkConfig(
            packageName = PACKAGE_NAME,
            metrics = listOf(StartupTimingMetric()),
            compilationMode = compilationMode,
            iterations = 3
        ),
        startupMode
    ) {
        pressHome()
        launchPackageAndWait()
    }

    companion object {
        private const val PACKAGE_NAME = "androidx.benchmark.integration.macrobenchmark.target"

        @Parameterized.Parameters(name = "compilation_mode={0}, startup_mode={1}")
        @JvmStatic
        fun kilProcessParameters(): List<Array<Any>> {
            val compilationModes = listOf(
                CompilationMode.None,
                CompilationMode.SpeedProfile(warmupIterations = 3)
            )
            val processKillOptions = listOf(StartupMode.WARM, StartupMode.COLD)
            return compilationModes.zip(processKillOptions).map {
                arrayOf(it.first, it.second)
            }
        }
    }
}
