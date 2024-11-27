package com.vishal2376.bookie.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.comic_sans
import bookie.composeapp.generated.resources.comic_sans_bold
import bookie.composeapp.generated.resources.sedgwick
import org.jetbrains.compose.resources.Font

@Composable
fun ComicSansFontFamily() = FontFamily(
	Font(Res.font.comic_sans, weight = FontWeight.Normal),
	Font(Res.font.comic_sans_bold, weight = FontWeight.Bold)
)

@Composable
fun SedgwickFontFamily() = FontFamily(
	Font(Res.font.sedgwick, weight = FontWeight.Normal)
)

@Composable
fun BookieTypography() = Typography().run {
	val sedgwick = SedgwickFontFamily()
	val comicSans = ComicSansFontFamily()

	copy(
		headlineLarge = headlineLarge.copy(
			fontFamily = sedgwick,
			fontWeight = FontWeight.Normal
		),
		headlineMedium = headlineMedium.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Bold
		),
		headlineSmall = headlineSmall.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Bold
		),
		titleLarge = titleLarge.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Bold
		),
		titleMedium = titleMedium.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Bold
		),
		titleSmall = titleSmall.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		bodyLarge = bodyLarge.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		bodyMedium = bodyMedium.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		bodySmall = bodySmall.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		labelLarge = labelLarge.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		labelMedium = labelMedium.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		),
		labelSmall = labelSmall.copy(
			fontFamily = comicSans,
			fontWeight = FontWeight.Normal
		)
	)
}
