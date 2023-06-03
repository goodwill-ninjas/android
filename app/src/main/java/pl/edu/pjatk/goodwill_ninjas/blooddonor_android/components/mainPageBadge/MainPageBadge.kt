package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.mainPageBadge

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R


@Composable
fun MainPageBadge(featRanksNumber: Int) {
    var image: Painter

    val grayScaleMatrix = ColorMatrix(
        floatArrayOf(
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )

    if (featRanksNumber == 0) {
        image = painterResource(R.drawable.odznaka_1)
        Image(
            image,
            "odznaka",
            modifier = Modifier
                .aspectRatio(ratio =
                image.intrinsicSize.height /
                        image.intrinsicSize.width)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.colorMatrix(grayScaleMatrix)
        )
    }
    if (featRanksNumber == 1) {
        image = painterResource(id = R.drawable.odznaka_1)
        Image(image, "odznaka",
            modifier = Modifier
                .aspectRatio(ratio =
                image.intrinsicSize.height /
                        image.intrinsicSize.width)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Fit
        )
    }
    if (featRanksNumber == 2) {
        image = painterResource(id = R.drawable.odznaka_2)
        Image(image, "odznaka",
            modifier = Modifier
            .aspectRatio(ratio =
            image.intrinsicSize.height /
                    image.intrinsicSize.width)
            .fillMaxWidth()
            .fillMaxHeight(),
            contentScale = ContentScale.Fit)
    }
    if (featRanksNumber == 3) {
        image = painterResource(id = R.drawable.odznaka_3)
        Image(image, "odznaka",
            modifier = Modifier
                .aspectRatio(ratio =
                    image.intrinsicSize.height /
                    image.intrinsicSize.width)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Fit
        )
    }
}