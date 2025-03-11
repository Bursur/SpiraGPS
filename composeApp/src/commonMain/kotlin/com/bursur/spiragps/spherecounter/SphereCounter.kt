package com.bursur.spiragps.spherecounter

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun SphereCounter(modifier: Modifier = Modifier, route: Route) {
    Column(modifier = modifier.width(400.dp)) {
        if(route.powerSpheres > 0)
            Counter("Power Spheres", route.powerSpheres)

        if(route.speedSpheres > 0)
            Counter("Speed Spheres", route.speedSpheres)
    }
}

@Composable
private fun Counter(sphereType: String, requirement: Int) {
    val bgColour by animateColorAsState(SpiraGPSColours.editorControlBackground)
    val textColor by animateColorAsState(SpiraGPSColours.text)
    var count by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition()

    val pulse by infiniteTransition.animateColor(
        initialValue = SpiraGPSColours.editorControlBackground,
        targetValue = SpiraGPSColours.toggleSelectedTrackColour,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(3.dp)
            .background(
                if(count >= requirement) pulse else bgColour,
                RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {
        // Sphere Type
        Text(
            text = sphereType,
            color = textColor,
            style = SpiraGPSText.typography.info,
            modifier = Modifier.padding(start = 5.dp)
        )

        // Down Arrow
        IconButton(
            onClick = {
            if(count > 0)
                --count
            }
        ) {
            Icon(imageVector = Icons.Default.Remove, contentDescription = null, tint = textColor)
        }

        // Count
        Text(text = count.toString(), color = textColor, style = SpiraGPSText.typography.contentsTitle, fontWeight = FontWeight.Bold)

        // Up Arrow
        IconButton(
            onClick = {
                ++count
            }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = textColor)
        }

        // Set Text
        Text(
            text = "Add Batch: ",
            style = SpiraGPSText.typography.info,
            color = textColor
        )

        var jumpText by remember { mutableStateOf("") }
        TextEdit(
            text = jumpText,
            placeholderText = "",
            multiLine = false,
            modifier = Modifier.widthIn(max = 40.dp).padding(end = 5.dp),
            onEnterKey = {
                try {
                    count += jumpText.toInt()
                }
                catch(_: Exception) { }

                count = count.coerceAtLeast(0)
                jumpText = ""
            }
        ) {
            jumpText = it
        }
    }
}