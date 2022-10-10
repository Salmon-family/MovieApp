package com.karrar.movieapp.domain.models

import com.karrar.movieapp.data.remote.response.episode.EpisodeDto

data class Season(
    val seasonId: Int,
    val imageUrl: String,
    val seasonName: String,
    val seasonYear: String,
    val seasonNumber: Int,
    val episodeCount: Int,
    val seasonDescription: String,
    val episodes: List<EpisodeDto?>
)