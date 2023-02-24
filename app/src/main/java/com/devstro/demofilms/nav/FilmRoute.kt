package com.devstro.demofilms.nav

object FilmRoute {
    const val HOME = "home"
    const val FILM_DETAIL = "filmDetail/{${FilmNavParam.FILM_ID}}"
}

object FilmNavParam {
    const val FILM_ID = "filmId"
}
