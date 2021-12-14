package net.d4rk.portfoliomanager.ui.portfolio.model

data class Asset (
    val id: Int,
    val exchange: String,
    val symbol: String,
    val amount: Double,
    val price: Double
) {

}