package app.lawnchair.ui.blur

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 * Gaussian Blur Backdrop utility for App Drawer and Dock
 * Blurs the background layer (wallpaper/workspace) without system dependency
 * Uses Compose graphicsLayer and blur modifier for smooth, iOS-like effect
 */
object BlurBackdropUtil {

    /**
     * Composable that renders a blurred backdrop
     * @param imageUrl Optional URL to background image
     * @param blurRadius Blur radius in Dp (default 18.dp for iOS-like effect)
     * @param alpha Opacity of the blurred backdrop (0f-1f)
     * @param tintColor Optional color overlay tint
     * @param modifier Optional modifier for customization
     */
    @Composable
    fun BlurredBackdrop(
        imageUrl: String? = null,
        blurRadius: Dp = 18.dp,
        alpha: Float = 0.7f,
        tintColor: Color = Color.Black.copy(alpha = 0.1f),
        modifier: Modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha))
    ) {
        Box(modifier = modifier) {
            if (!imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Blurred background",
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(blurRadius),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(tintColor)
            )
        }
    }

    /**
     * Creates a blur-style backdrop surface for App Drawer
     * Provides iOS-like frosted glass effect with slight transparency
     */
    @Composable
    fun DrawerBlurBackdrop(
        imageUrl: String? = null,
        modifier: Modifier = Modifier
    ) {
        BlurredBackdrop(
            imageUrl = imageUrl,
            blurRadius = 22.dp,
            alpha = 0.75f,
            tintColor = Color.Black.copy(alpha = 0.08f),
            modifier = modifier.fillMaxSize()
        )
    }

    /**
     * Creates a blur-style backdrop surface for Dock/Hotseat
     * Lighter blur effect for quick access area
     */
    @Composable
    fun DockBlurBackdrop(
        imageUrl: String? = null,
        modifier: Modifier = Modifier
    ) {
        BlurredBackdrop(
            imageUrl = imageUrl,
            blurRadius = 20.dp,
            alpha = 0.72f,
            tintColor = Color.White.copy(alpha = 0.05f),
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
        )
    }
}
