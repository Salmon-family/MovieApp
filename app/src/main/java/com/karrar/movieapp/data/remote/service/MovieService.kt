package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.login.RequestTokenResponse
import com.karrar.movieapp.data.remote.response.login.SessionResponse
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorDetailsDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.data.remote.response.login.LogoutResponse
import com.karrar.movieapp.domain.enums.TrendingTimeWindow
import retrofit2.Response
import retrofit2.http.DELETE
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
    ): Response<ActorDetailsDto>

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(
        @Path("person_id") actorId: Int,
    ): Response<ActorMoviesDto>
    @GET("discover/movie")
    suspend fun getMovieListByGenre(@Query("with_genres") genreID: Int): Response<BaseResponse<MovieDto>>

    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionId: String?,
    ): Response<AccountDto>

    @GET("account/{account_id}/rated/movies")
    suspend fun getRatedMovies(
        @Query("session_id") sessionId: String?,
    ): Response<BaseResponse<RatedMoviesDto>>

    @DELETE("authentication/session")
    suspend fun deleteSession(
        @Query("session_id") sessionId: String,
    ): Response<LogoutResponse>
}



