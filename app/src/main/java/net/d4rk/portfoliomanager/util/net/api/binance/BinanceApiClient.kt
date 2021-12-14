package net.d4rk.portfoliomanager.util.net.api.binance

import net.d4rk.portfoliomanager.util.net.api.ApiClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BinanceApiClient {

    @GET("api/v3/ticker/price")
    fun getPrice(@Query("symbol") symbol: String): Call<BinanceApiResponse.Ticker>

    @GET("api/v3/ticker/price")
    fun getPrices(): Call<List<BinanceApiResponse.Ticker>>

    @Headers("X-MBX-APIKEY: APIKEY")
    @GET("api/v3/account")
    fun getAccount(@Query("recvWindow") recvWindow: Int, @Query("timestamp") timestamp: Long, @Query("signature") signature: String): Call<BinanceApiResponse.Account>

    @Headers("X-MBX-APIKEY: APIKEY")
    @GET("sapi/v1/lending/union/account")
    fun getSavingAccount(@Query("recvWindow") recvWindow: Int, @Query("timestamp") timestamp: Long, @Query("signature") signature: String): Call<BinanceApiResponse.SavingAccount>

    companion object {

        var BASE_URL = "https://api.binance.com"

        fun create() : BinanceApiClient {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(BinanceApiClient::class.java)
        }
    }

}