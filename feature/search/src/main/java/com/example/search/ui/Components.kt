package com.example.search.ui

import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.search.GithubUserItem
import com.example.search.R

@Composable
fun EmptyCardRow(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(modifier = modifier
        .height(230.dp)
        .fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}


@Composable
fun UserCardRow(
    users: List<GithubUserItem>,
    onUserCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(start = 12.dp, end = 12.dp)) {
        items(users) { userItem ->
            UserCardItem(userItem = userItem, onUserCardClick = onUserCardClick)
        }
    }
}


@Composable
fun UserCardItem(
    userItem: GithubUserItem,
    onUserCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(width = 170.dp, height = 230.dp)
            .clickable(onClick = { onUserCardClick(userItem.userId) })
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            )
            UserImage(
                imageUrl = userItem.avatarUrl,
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.BottomCenter)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userItem.userName,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun UserImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    elevation: Dp = 5.dp,
    shape: Shape = CircleShape
) {
    Surface(shadowElevation = elevation, shape = shape, modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
            contentDescription = "icon",
            placeholder = painterResource(id = R.drawable.ic_android_black_24dp),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}


@Preview(widthDp = 300, heightDp = 300, uiMode = UI_MODE_TYPE_NORMAL)
@Composable
private fun SnackImagePreview(
) {
    val sampleUserItem = GithubUserItem(
        userName = "android",
        userId = 123,
        userUrl = "",
        avatarUrl = "",
        followersUrl = "",
        followingUrl = "",
        repositoryUrl = ""
    )
    UserCardItem(userItem = sampleUserItem, onUserCardClick = {})
}

@Preview
@Composable
private fun EmptyRowPreview() {
    EmptyCardRow(text = "No following")
}
