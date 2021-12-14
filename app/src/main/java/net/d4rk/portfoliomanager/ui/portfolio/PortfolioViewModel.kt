package net.d4rk.portfoliomanager.ui.portfolio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.d4rk.portfoliomanager.ui.portfolio.repository.AssetRepository
import net.d4rk.portfoliomanager.util.net.api.binance.BinanceApiResponse
import net.d4rk.portfoliomanager.util.security.SignatureGenerator
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.SignatureException
import java.time.Instant
import java.time.LocalDateTime

class PortfolioViewModel constructor(private val repository: AssetRepository): ViewModel() {

    private val TAG = PortfolioViewModel::class.simpleName

    val mAssetList = MutableLiveData<List<BinanceApiResponse.Ticker>>()
    val mAccountList = MutableLiveData<List<BinanceApiResponse.Asset>>()


    private val mErrorMessage = MutableLiveData<String>()

    fun getTickerList() {
//        val response: List<BinanceApiResponse.Ticker> = mutableListOf(
//            BinanceApiResponse.Ticker(symbol = "BTCUSDT", price = 51231.02),
//            BinanceApiResponse.Ticker(symbol = "ADAUSDT", price = 1.41)
//        )
//
//        mAssetList.postValue(response)

//        response.enqueue(object : Callback<List<BinanceApiResponse.Ticker>> {
//            override fun onResponse(call: Call<List<BinanceApiResponse.Ticker>>, response: Response<List<BinanceApiResponse.Ticker>>) {
//                mAssetList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<BinanceApiResponse.Ticker>>, t: Throwable) {
//                mErrorMessage.postValue(t.message)
//            }
//        })

//        val response = repository.getBinanceTicker("BTCUSDT")
//
//        response.enqueue(object : Callback<List<BinanceApiResponse.Ticker>> {
//            override fun onResponse(call: Call<List<BinanceApiResponse.Ticker>>, response: Response<List<BinanceApiResponse.Ticker>>) {
//                mAssetList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<BinanceApiResponse.Ticker>>, t: Throwable) {
//                mErrorMessage.postValue(t.message)
//            }
//        })
    }

    fun updateAccountList() {
        val timestamp = System.currentTimeMillis()

        Log.d(TAG, "updateAccountList: recvWindow: 10000")
        Log.d(TAG, "updateAccountList: timestamp: $timestamp")
        Log.d(TAG, "updateAccountList: key: $timestamp")
        Log.d(TAG, "updateAccountList: data: recvWindow=10000&timestamp=$timestamp")
        Log.d(TAG, "updateAccountList: signature: ${SignatureGenerator.createSignature("PRIVATEKEY", "recvWindow=10000&timestamp=$timestamp")}")

//        val response = repository.getBinanceAccount(10000, timestamp, SignatureGenerator.createSignature("PRIVATEKEY", "recvWindow=10000&timestamp=$timestamp"))
        val response = repository.getBinanceSavingAccount(10000, timestamp, SignatureGenerator.createSignature("PRIVATEKEY", "recvWindow=10000&timestamp=$timestamp"))

//        response.enqueue(object : Callback<BinanceApiResponse.Account> {
        response.enqueue(object : Callback<BinanceApiResponse.SavingAccount> {
            override fun onResponse(
//                    call: Call<BinanceApiResponse.Account>,
                    call: Call<BinanceApiResponse.SavingAccount>,
//                    response: Response<BinanceApiResponse.Account>
                    response: Response<BinanceApiResponse.SavingAccount>
            ) {
                Log.d(TAG, "onResponse: call: ${call.toString()}")
                Log.d(TAG, "onResponse: response: ${response.toString()}")
                Log.d(TAG, "onResponse: headers: ${response.headers()}")
                Log.d(TAG, "onResponse: body: ${response.body()}")
                Log.d(TAG, "onResponse: errorbody: ${response.errorBody()}")
                Log.d(TAG, "onResponse: message: ${response.message()}")
                Log.d(TAG, "onResponse: code: ${response.code()}")

                if (response.code() == 200) {
//                    mAccountList.postValue(response.body()!!.balances.filter {it.free.toDouble() > 0.0})

                    val assets = response.body()!!.positionAmountVos
                    val savingAssets: List<BinanceApiResponse.Asset> = assets.map { BinanceApiResponse.Asset(it.asset, it.amount, "0.0")}
                    mAccountList.postValue(savingAssets.filter {it.free.toDouble() > 0.0})
                } else {
                    try {
                        Log.e(TAG, "onResponse: ${response.errorBody()}")
                        Log.e(TAG, "onResponse: ${response.errorBody()!!.string()}")

                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Log.e(TAG, "onResponse: ${jObjError.getJSONObject("error").getString("message")}")
                    } catch (e: Exception) {
                        Log.e(TAG, "onResponse: errorbody is empty")
                    }

                }

            }

//            override fun onFailure(call: Call<BinanceApiResponse.Account>, t: Throwable) {
            override fun onFailure(call: Call<BinanceApiResponse.SavingAccount>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
        )
    }

    fun updateTickerList() {
//        val response: List<BinanceApiResponse.Ticker> = mutableListOf(
//            BinanceApiResponse.Ticker(symbol = "BTCUSDT", price = 51231.02),
//            BinanceApiResponse.Ticker(symbol = "ADAUSDT", price = 1.41),
//            BinanceApiResponse.Ticker(symbol = "BNBUSDT", price = 585.9)
//        )
//
//        mAssetList.postValue(response)

        val response = repository.getBinanceTickers()

        response.enqueue(object : Callback<List<BinanceApiResponse.Ticker>> {
            override fun onResponse(
                call: Call<List<BinanceApiResponse.Ticker>>,
                response: Response<List<BinanceApiResponse.Ticker>>
            ) {
                Log.d(TAG, "onResponse: call: ${call.toString()}")
                Log.d(TAG, "onResponse: response: ${response.toString()}")
                Log.d(TAG, "onResponse: headers: ${response.headers()}")
                Log.d(TAG, "onResponse: body: ${response.body()}")
                Log.d(TAG, "onResponse: errorbody: ${response.errorBody()}")
                Log.d(TAG, "onResponse: message: ${response.message()}")
                Log.d(TAG, "onResponse: code: ${response.code()}")

                mAssetList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<BinanceApiResponse.Ticker>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
        )

//        val response = repository.getBinanceTicker("BTCUSDT")
//
//        response.enqueue(object : Callback<BinanceApiResponse.Ticker> {
//            override fun onResponse(
//                call: Call<BinanceApiResponse.Ticker>,
//                response: Response<BinanceApiResponse.Ticker>
//            ) {
//                Log.d(TAG, "onResponse: call: ${call.toString()}")
//                Log.d(TAG, "onResponse: response: ${response.toString()}")
//                Log.d(TAG, "onResponse: headers: ${response.headers()}")
//                Log.d(TAG, "onResponse: body: ${response.body()}")
//                Log.d(TAG, "onResponse: errorbody: ${response.errorBody()}")
//                Log.d(TAG, "onResponse: message: ${response.message()}")
//                Log.d(TAG, "onResponse: code: ${response.code()}")
//
//                mAssetList.postValue(mutableListOf(response.body()!!))
//            }
//
//            override fun onFailure(call: Call<BinanceApiResponse.Ticker>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        )

//        response.enqueue(object : Callback<BinanceApiResponse.Ticker> {
//            override fun onResponse(call: Call<BinanceApiResponse.Ticker>, response: Response<BinanceApiResponse.Ticker>) {
//
////                mAssetList.postValue(mutableListOf(response.body()!!))
//            }
//
//            override fun onFailure(call: Call<BinanceApiResponse.Ticker>, t: Throwable) {
//                mErrorMessage.postValue(t.message)
//            }
//        })
    }
}