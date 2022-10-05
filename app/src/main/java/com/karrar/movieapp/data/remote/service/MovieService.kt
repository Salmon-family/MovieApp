package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.movieDetailsDto.cast.CreditsDto
import com.karrar.movieapp.data.remote.response.movieDetailsDto.MovieDetailsDto
import com.karrar.movieapp.data.remote.response.movieDetailsDto.RatingDto
import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.ReviewsDto
import com.karrar.movieapp.data.remote.response.trailerVideosDto.TrailerDto
import com.karrar.movieapp.domain.enums.TrendingTimeWindow
import retrofit2.Response
import retrofit2.http.*


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<BaseResponse<MovieDto>>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = TrendingTimeWindow.DAY.value,
    ): Response<BaseResponse<MovieDto>>


    @GET("trending/person/{time_window}")
    suspend fun getTrendingPersons(
        @Path("time_window") timeWindow: String = TrendingTimeWindow.DAY.value,
    ): Response<BaseResponse<PersonDto>>


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetailsDto>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
    ): Response<CreditsDto>


    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: Int,
    ): Response<BaseResponse<MovieDto>>


    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
    ): Response<BaseResponse<ReviewsDto>>


    @FormUrlEncoded
    @POST("movie/{movie_id}/rating")
    suspend fun postRating(
        @Path("movie_id") movieId: Int,
        @Field ("value") rating: Float,
        @Query("guest_session_id") apiKey: String?,
        ): Response<RatingDto>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
    ): Response<TrailerDto>


    @GET("account/{account_id}/lists")
    suspend fun getCreatedLists(
        @Path("account_id") accountId: Int,
        @Query("session_id") session_id: String
    ): Response<BaseResponse<CreatedListDto>>


    @FormUrlEncoded
    @POST("list")
    suspend fun createList(
        @Query("session_id") sessionId: String,
        @Field("name") name: String,
        @Field("description") description: String,
    ) : Response<CreateListDto>



    @FormUrlEncoded
    @POST("list/{list_id}/add_item")
    suspend fun addMovieToList(
        @Path("list_id") ListId: Int,
        @Query("session_id") sessionId: String,
        @Field("media_id") movieId: Int,
    ) : Response<AddMovieDto>


    @GET("list/{list_id}")
    suspend fun getList(
        @Path("list_id") listId: Int,
    ): Response<ListDetailsDto>


}


