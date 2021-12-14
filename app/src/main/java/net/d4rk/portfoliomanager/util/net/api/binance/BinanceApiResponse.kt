package net.d4rk.portfoliomanager.util.net.api.binance

class BinanceApiResponse {

    data class Ticker(
        val symbol: String,
        val price: Double
    )

    data class Kline(
        val opentime: Long,
        val open: String,
        val high: String,
        val low: String,
        val close: String,
        val volume: String,
        val closetime: Long,
        val quoteassetvolume: String,
        val numberoftrades: Int,
        val takerbuybaseassetvolume: String,
        val takerbuyquoteassetvolume: String,
        val ignore: String
    )

    data class Account (
            val makerCommission: Long,
            val takerCommission: Long,
            val buyerCommission: Long,
            val sellerCommission: Long,
            val canTrade: Boolean,
            val canWithdraw: Boolean,
            val canDeposit: Boolean,
            val updateTime: Long,
            val accountType: String,
            val balances: List<Asset>,
            val permissions: List<String>
    )

    data class SavingAccount (
        val positionAmountVos: List<SavingAsset>,
        val totalAmountInBTC: String,
        val totalAmountInUSDT: String,
        val totalFixedAmountInBTC: String,
        val totalFixedAmountInUSDT: String,
        val totalFlexibleInBTC: String,
        val totalFlexibleInUSDT: String
    )

    data class Asset(
        val asset: String,
        val free: String,
        val locked: String
    )

    data class SavingAsset (
        val amount: String,
        val amountInBTC: String,
        val amountInUSDT: String,
        val asset: String
    )

    data class SavingAssetDetail (
        val asset: String,
        val canTransfer: Boolean,
        val createTimestamp: Long,
        val duration: Long,
        val endTime: Long,
        val interest: String,
        val interestRate: String,
        val lot: Long,
        val positionId: Long,
        val principal: String,
        val projectId: String,
        val projectName: String,
        val purchaseTime: Long,
        val redeemDate: String,
        val startTime: Long,
        val status: String,
        val type: String
    )

}