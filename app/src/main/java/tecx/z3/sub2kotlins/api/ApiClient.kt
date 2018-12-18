package tecx.z3.sub2kotlins.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tecx.z3.sub2kotlins.BuildConfig

/**
 * Created by Z3_Brothers on 11/20/18.
 */
class ApiClient {
    fun setInit(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): ApiInterface{
        return setInit().create(ApiInterface::class.java)
    }
}