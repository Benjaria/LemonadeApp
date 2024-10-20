package com.example.lemonade.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.R

@Preview
@Composable
fun LemonadeApp() {
    Lemonade(modifier = Modifier.fillMaxSize())
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {

    var stage by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageResource = when (stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResource = when (stage) {
        1 -> R.string.tap_the_lemon_tree
        2 -> R.string.squeeze_lemon
        3 -> R.string.drink_lemonade
        else -> R.string.restart
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (stage == 2) {
            squeezeCount = (2..4).random()
            MyButton(imageResource, stage) { if (squeezeCount > 0) squeezeCount-- else stage += 1 }
            MySpacer()
            TextComposable(stringResource)

        } else if (stage > 4) {
            stage = 1
        } else {
            MyButton(imageResource, stage) { stage += 1 }
            MySpacer()
            TextComposable(stringResource)
        }
    }
}

@Composable
fun TextComposable(text: Int) {
    Text(stringResource(text), fontSize = 18.sp)
}

@Composable
fun ImageComposable(image: Int, contentDesc: Int) {
    when (contentDesc) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
    Image(
        painterResource(image), stringResource(contentDesc), modifier = Modifier
            .width(
                dimensionResource(R.dimen.button_image_width)
            )
            .height(dimensionResource(R.dimen.button_image_height))
            .padding(dimensionResource(R.dimen.button_interior_padding))
    )
}

@Composable
fun MySpacer() {
    Spacer(Modifier.height(dimensionResource(R.dimen.padding_vertical)))
}

@Composable
fun MyButton(imageResource: Int, stage: Int, onImageClick: () -> Unit) {
    Button(
        onClick = onImageClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        ImageComposable(imageResource, stage)
    }
}
