package com.vishal2376.bookie.book.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.book_sample
import com.vishal2376.bookie.book.domain.Book
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookItemUI(book: Book, modifier: Modifier = Modifier) {
	val gradientColor = listOf(Color.Transparent, MaterialTheme.colorScheme.primaryContainer)
	Column(
		modifier = modifier
			.wrapContentSize()
			.width(160.dp)
			.clip(RoundedCornerShape(16.dp))
	) {
		//image
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp)
		) {
			// book cover
			Image(
				painter = painterResource(Res.drawable.book_sample),
				contentScale = ContentScale.Crop,
				contentDescription = null
			)
			// cover gradient blend
			Box(
				modifier = Modifier
					.height(100.dp)
					.fillMaxWidth()
					.align(Alignment.BottomCenter)
					.background(Brush.verticalGradient(gradientColor))
			)
			// rating
			Row(
				modifier = Modifier
					.padding(8.dp)
					.background(
						MaterialTheme.colorScheme.primary,
						RoundedCornerShape(8.dp)
					)
					.align(Alignment.BottomEnd)
					.padding(4.dp, 2.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(2.dp)
			) {
				Icon(
					modifier = Modifier.size(16.dp),
					imageVector = Icons.Filled.Star,
					tint = MaterialTheme.colorScheme.onPrimary,
					contentDescription = null
				)
				Text(
					text = book.averageRating.toString(),
					style = MaterialTheme.typography.labelMedium,
					color = MaterialTheme.colorScheme.onPrimary,
					fontWeight = FontWeight.Bold
				)
			}
		}
		//info
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.primaryContainer)
				.padding(8.dp)
		) {
			Text(
				text = book.title, style = MaterialTheme.typography.labelMedium,
				maxLines = 1,
				fontWeight = FontWeight.Bold,
				overflow = TextOverflow.Ellipsis
			)
			Text(
				text = book.authors.first(), style = MaterialTheme.typography.labelSmall,
				color = MaterialTheme.colorScheme.onPrimaryContainer
			)
		}
	}
}
