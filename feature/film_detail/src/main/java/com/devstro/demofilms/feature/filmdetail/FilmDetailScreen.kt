package com.devstro.demofilms.feature.filmdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(id: Int, viewModel: FilmDetailViewModel = hiltViewModel()) {
    val state = viewModel.getState()
    LaunchedEffect(Unit) {
        viewModel.dispatchIntent(FilmDetailIntent.FetchFilmDetail(id))
    }
    Scaffold { paddingValues ->
        if (state.isLoading) {
            Text("Loading...")
        } else {
            Column {
                Header(state.data?.backdropPath, paddingValues)
                Body(state)
            }
        }
    }
}

@Composable
private fun Body(state: FilmDetailState) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = state.data?.title.orEmpty(),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 4.dp)
        )

        Text(
            text = "Overview",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 4.dp)
        )

        Text(
            text = state.data?.overview.orEmpty(),
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Header(posterPath: String?, paddingValues: PaddingValues) {
    Box {
        posterPath?.let {
            GlideImage(
                model = "https://image.tmdb.org/t/p/w300/$posterPath",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        TopBar()
    }
}

@Composable
private fun TopBar() {
    val IconButtonSize = 48.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*navController.navigateUp()*/ }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        Text(
            text = LocalContext.current.getString(R.string.film_detail_title),
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding(end = IconButtonSize)
        )

        Box(
            modifier = Modifier
                .size(IconButtonSize)
        )
    }
}
