package net.d4rk.portfoliomanager.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.d4rk.portfoliomanager.ui.portfolio.PortfolioViewModel
import net.d4rk.portfoliomanager.ui.portfolio.repository.AssetRepository

class ViewModelFactory constructor(private val repository: AssetRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            PortfolioViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}