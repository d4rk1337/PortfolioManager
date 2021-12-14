package net.d4rk.portfoliomanager.ui.portfolio.repository

import net.d4rk.portfoliomanager.util.net.api.binance.BinanceApiClient

class AssetRepository constructor(private val apiClient: BinanceApiClient) {

    fun getBinanceTicker(symbol: String) = apiClient.getPrice(symbol)

    fun getBinanceTickers() = apiClient.getPrices()

    fun getBinanceAccount(recvWindow: Int, timestamp: Long, signature: String) = apiClient.getAccount(recvWindow, timestamp, signature)

    fun getBinanceSavingAccount(recvWindow: Int, timestamp: Long, signature: String) = apiClient.getSavingAccount(recvWindow, timestamp, signature)

}