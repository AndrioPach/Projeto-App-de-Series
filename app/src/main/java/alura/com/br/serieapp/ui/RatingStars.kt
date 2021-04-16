package alura.com.br.serieapp.ui

import androidx.appcompat.widget.AppCompatRatingBar

fun RatingStars(series: AppCompatRatingBar, vote: Float) {
    series.numStars = 5
    series.max = 5
    series.stepSize = 0.01F
    series.rating = (vote/2)
}