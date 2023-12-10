package com.techlads.jetsleepsounds.songs

import androidx.annotation.DrawableRes
import com.techlads.jetsleepsounds.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

object TracksFactory {
    private val songs = lazy {
        listOf(
            Track(
                id = 1,
                title = "Soft Rain Ambient",
                url = "https://cdn.pixabay.com/download/audio/2022/05/13/audio_257112ce99.mp3?filename=soft-rain-ambient-111154.mp3",
                duration = 9.minutes,
                src = R.drawable.soft_rain_ambient
            ),
            Track(
                id = 2,
                title = "Sandy Beach Calm Waves",
                url = "https://cdn.pixabay.com/download/audio/2021/09/06/audio_37aad22374.mp3?filename=sandy-beach-calm-waves-water-nature-sounds-8052.mp3",
                duration = 1.minutes.plus(30.seconds),
                src = R.drawable.sandy_beach_calm_waves
            ),
            Track(
                id = 3,
                title = "Light Rain Ambient",
                url = "https://cdn.pixabay.com/download/audio/2022/07/04/audio_f52a5754b1.mp3?filename=light-rain-ambient-114354.mp3",
                duration = 9.minutes,
                src = R.drawable.light_rain_ambient
            ),
            Track(
                id = 4,
                title = "Birds Singing Calm River",
                url = "https://cdn.pixabay.com/download/audio/2022/11/28/audio_43c341f09e.mp3?filename=birds-singing-calm-river-nature-ambient-sound-127411.mp3",
                duration = 3.minutes,
                src = R.drawable.birds_singing_calm_river
            ),
            Track(
                id = 5,
                title = "Rain forest birds",
                url = "https://cdn.pixabay.com/download/audio/2022/05/17/audio_28d2030bd4.mp3?filename=rain-in-forest-birds-nature-111405.mp3",
                duration = 3.minutes,
                src = R.drawable.rain_forest_birds
            ),
            Track(
                id = 6,
                title = "Scary forest",
                url = "https://cdn.pixabay.com/download/audio/2022/03/15/audio_13a1300307.mp3?filename=scary-forest-90162.mp3",
                duration = 2.minutes.plus(31.seconds),
                src = R.drawable.scary_forest
            ),
            Track(
                id = 7,
                title = "Rain and thunder",
                url = "https://cdn.pixabay.com/download/audio/2022/02/07/audio_1393bdaae1.mp3?filename=rain-and-thunder-16705.mp3",
                duration = 12.minutes.plus(6.seconds),
                src = R.drawable.rain_and_thunder
            ),
            Track(
                id = 8,
                title = "Birds",
                url = "https://cdn.pixabay.com/download/audio/2022/02/10/audio_7a07ee0e79.mp3?filename=birds-19624.mp3",
                duration = 10.minutes.plus(36.seconds),
                src = R.drawable.birds
            ),
            Track(
                id = 8,
                title = "Campfire Crackling",
                url = "https://cdn.pixabay.com/download/audio/2022/09/10/audio_113b247b69.mp3?filename=campfire-crackling-fireplace-sound-119594.mp3",
                duration = 2.minutes.plus(19.seconds),
                src = R.drawable.campfire_crackling
            ),
        )
    }

    fun getTracks(): List<Track> =  songs.value + songs.value + songs.value + songs.value
}

data class Track(
    val id: Int,
    val title: String,
    val url: String,
    val duration: Duration,
    @DrawableRes
    val src: Int
)