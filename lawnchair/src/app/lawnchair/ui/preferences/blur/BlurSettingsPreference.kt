/*
 * Copyright (C) 2025 Lawnchair
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

package app.lawnchair.ui.preferences.blur

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.lawnchair.preferences.PreferenceManager
import com.patrykmichalik.opto.domain.Preference

@Composable
fun BlurSettingsPreference(
    modifier: Modifier = Modifier,
    prefs: PreferenceManager = PreferenceManager.getInstance(null),
) {
    val blurRadius by prefs.getBlurRadius().asState()
    val blurAlpha by prefs.getBlurAlpha().asState()
    val blurSampleFactor by prefs.getBlurSampleFactor().asState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Blur Settings")
        
        Spacer(modifier = Modifier.height(16.dp))

        // Blur Radius Slider (8-32)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Blur Radius: ${blurRadius.toInt()}", modifier = Modifier.weight(1f))
            Slider(
                value = blurRadius.toFloat(),
                onValueChange = { prefs.getBlurRadius().setBlocking(it.toDouble()) },
                valueRange = 8f..32f,
                steps = 23,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Blur Alpha Slider (0-255)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Blur Opacity: ${(blurAlpha / 255f * 100).toInt()}%", modifier = Modifier.weight(1f))
            Slider(
                value = blurAlpha.toFloat(),
                onValueChange = { prefs.getBlurAlpha().setBlocking(it.toInt()) },
                valueRange = 0f..255f,
                steps = 254,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Sample Factor Slider (1-8)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sample Factor: ${blurSampleFactor.toInt()}", modifier = Modifier.weight(1f))
            Slider(
                value = blurSampleFactor.toFloat(),
                onValueChange = { prefs.getBlurSampleFactor().setBlocking(it.toInt()) },
                valueRange = 1f..8f,
                steps = 6,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

// Extension functions for PreferenceManager
fun PreferenceManager.getBlurRadius(): Preference<Double> {
    return object : Preference<Double> {
        override fun get(): Double = prefs.getDouble("blur_radius", 18.0)
        override fun setBlocking(value: Double) {
            prefs.edit().putDouble("blur_radius", value).apply()
        }
    }
}

fun PreferenceManager.getBlurAlpha(): Preference<Int> {
    return object : Preference<Int> {
        override fun get(): Int = prefs.getInt("blur_alpha", 120)
        override fun setBlocking(value: Int) {
            prefs.edit().putInt("blur_alpha", value).apply()
        }
    }
}

fun PreferenceManager.getBlurSampleFactor(): Preference<Int> {
    return object : Preference<Int> {
        override fun get(): Int = prefs.getInt("blur_sample_factor", 1)
        override fun setBlocking(value: Int) {
            prefs.edit().putInt("blur_sample_factor", value).apply()
        }
    }
}
