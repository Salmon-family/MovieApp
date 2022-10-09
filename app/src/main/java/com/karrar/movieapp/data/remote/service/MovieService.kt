package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.login.RequestTokenResponse
import com.karrar.movieapp.data.remote.response.login.SessionResponse
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.genre.GenreResponse
import com.karrar.movieapp.data.remote.response.actor.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.response.CreditsDto
import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.data.remote.response.trailerVideosDto.TrailerDto
import com.karrar.movieapp.domain.enums.TrendingTimeWindow
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


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
    suspend fun getTrendingActors(
        @Path("time_window") timeWindow: String = TrendingTimeWindow.DAY.value,
    ): Response<BaseResponse<ActorDto>>

    @GET("search/person")
    suspend fun searchForPerson(
        @Query("query") query: String
    ): Response<BaseResponse<ActorDto>>

    @GET("search/movie")
    suspend fun searchForMovie(
        @Query("query") query: String
    ): Response<BaseResponse<TVShowsDTO>>

    @GET("search/tv")
    suspend fun searchForSeries(
        @Query("query") query: String
    ): Response<BaseResponse<TVShowsDTO>>

    @GET("authentication/token/new")
    suspend fun getRequestToken() : Response<RequestTokenResponse>
    @GET("genre/movie/list")
    suspend fun getGenreList(): Response<GenreResponse>

    @JvmSuppressWildcards
    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestTokenWithLogin(
        @FieldMap body:  Map<String,Any>
    ) : Response<RequestTokenResponse>

    @FormUrlEncoded
    @POST("authentication/session/new")
    suspend fun createSession(
        @Field("request_token")  requestToken : String
    ) : Response<SessionResponse>

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") actorId: Int,
    ): Response<ActorDto>

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(
        @Path("person_id") actorId: Int,
    ): Response<ActorMoviesDto>

    @GET("discover/movie")
    suspend fun getMovieListByGenre(@Query("with_genres") genreID: Int): Response<BaseResponse<MovieDto>>

    @GET("trending/all/day")
    suspend fun getDailyTrending(): Response<BaseResponse<TVShowsDTO>>

    @GET("discover/movie")
    suspend fun getAllMovies(): Response<BaseResponse<MovieDto>>

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
        @Query("session_id") apiKey: String?,
    ): Response<RatingDto>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
    ): Response<TrailerDto>


    @GET("account/{account_id}/lists")
    suspend fun getCreatedLists(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): Response<BaseResponse<CreatedListDto>>


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

    @GET("account/{account_id}/rated/movies")
    suspend fun getRatedMovie(
        @Path("account_id") listId: Int,
        @Query("session_id") sessionId: String,
    ): Response<BaseResponse<RatedMovie>>

    @FormUrlEncoded
    @POST("list")
    suspend fun createList(
        @Query("session_id") session_id: String,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("public") public: Boolean
    ) : Response<CreateListDto>

    @GET("account")
    suspend fun getMyAccount(
        @Query("session_id") session_id: String,
    ): Response<AccountDto>

    /**
     *          TV Show Services ...
     * */
    @GET("tv/on_the_air")
    suspend fun getOnTheAir(): Response<BaseResponse<TVShowsDTO>>

    @GET("tv/airing_today")
    suspend fun getAiringToday(): Response<BaseResponse<TVShowsDTO>>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShow():Response<BaseResponse<TVShowsDTO>>

    @GET("tv/popular")
    suspend fun getPopularTvShow():Response<BaseResponse<TVShowsDTO>>

    @GET("tv/latest")
    suspend fun getLatestTvShow():Response<BaseResponse<TVShowsDTO>>

    @GET("genre/tv/list")
    suspend fun getGenreTvShowList(): Response<GenreResponse>

    @GET("discover/tv")
    suspend fun getTvListByGenre(@Query("with_genres") genreId: Int): Response<BaseResponse<TVShowsDTO>>

    @GET("discover/tv")
    suspend fun getAllTvShows(): Response<BaseResponse<TVShowsDTO>>

}



