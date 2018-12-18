package tecx.z3.sub2kotlins.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tecx.z3.sub2kotlins.model.ResponseLastNext
import tecx.z3.sub2kotlins.model.ResponseLogo

/**
 * Created by Z3_Brothers on 11/22/18.
 */
interface ApiInterface {

    //Next 15
    @GET("api/v1/json/{key}/eventsnextleague.php")
    fun getNextMatch(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>

    //Last 15
    @GET("api/v1/json/{key}/eventspastleague.php")
    fun getLastMatch(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>

    //Detail
    @GET("api/v1/json/{key}/lookupevent.php")
    fun getDetailEvent(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>

    @GET("api/v1/json/{key}/lookupteam.php")
    fun getDetailTeam(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLogo>
}
