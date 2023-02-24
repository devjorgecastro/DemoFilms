package com.devstro.demofilms.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.devstro.demofilms.core.ui.UiState
import com.devstro.demofilms.feature.home.common.Urls
import com.devstro.demofilms.feature.home.ui.HorizontalFilmsPlaceholder
import com.devstro.films.core.domainCommon.entity.Film

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSelectedFilm: ((id: Int) -> Unit)? = null
) {
    val upcomingState = viewModel.upcomingFilmState
    val fimlsNowPlayingState = viewModel.filmsNowPlayingState
    val popularFilmsState = viewModel.popularFilmsState

    LaunchedEffect(Unit) {
        viewModel.dispatchIntent(HomeIntent.FetchUpcomingFilms)
        viewModel.dispatchIntent(HomeIntent.FetchFilmsNowPlaying)
        viewModel.dispatchIntent(HomeIntent.FetchPopularFilms)
    }

    Scaffold(
        topBar = { AppTopBar() }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            createCategoryFilms(upcomingState, paddingValue, onSelectedFilm)
            createCategoryFilms(fimlsNowPlayingState, paddingValue, onSelectedFilm)
            createCategoryFilms(popularFilmsState, paddingValue, onSelectedFilm)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                "Movies",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    )
}

@Composable
private fun createCategoryFilms(
    state: UiState,
    contentPadding: PaddingValues,
    onSelectedFilm: ((id: Int) -> Unit)? = null
) {
    when (state) {
        is UpcomingUiState -> {
            CategoryFilms(
                LocalContext.current.getString(R.string.title_upcoming),
                state.films,
                state.isLoading,
                contentPadding,
                onSelectedFilm
            )
        }
        is FilmsNowPlayingUiState -> {
            CategoryFilms(
                LocalContext.current.getString(R.string.title_films_now_playing),
                state.films,
                state.isLoading,
                contentPadding,
                onSelectedFilm
            )
        }
        is PopularFilmsUiState -> {
            CategoryFilms(
                LocalContext.current.getString(R.string.title_popular),
                state.films,
                state.isLoading,
                contentPadding,
                onSelectedFilm
            )
        }
    }
}

@Composable
fun CategoryFilms(
    categoryTitle: String,
    films: List<Film>,
    isLoading: Boolean,
    contentPadding: PaddingValues,
    onSelectedFilm: ((id: Int) -> Unit)? = null
) {
    val itemWidth = 150.dp
    val itemHeight = 250.dp

    Column(
        modifier = Modifier
            .padding(contentPadding)
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Text(
            categoryTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        if (isLoading) {
            HorizontalFilmsPlaceholder(itemWidth, itemHeight)
        } else {
            LazyRow {
                items(films) {
                    FilmListItem(it, itemWidth, itemHeight, onSelectedFilm)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun FilmListItem(
    film: Film,
    itemWidth: Dp,
    itemHeight: Dp,
    onSelectedFilm: ((id: Int) -> Unit)? = null
) {
    Card(
        onClick = { onSelectedFilm?.invoke(film.id) },
        modifier = Modifier
            .size(itemWidth, itemHeight)
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp
            )
    ) {
        Column {
            GlideImage(
                model = "${Urls.BASE_IMAGE}${film.posterPath}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            )
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = film.title,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
    }
}
