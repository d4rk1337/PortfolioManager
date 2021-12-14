package net.d4rk.portfoliomanager.ui.portfolio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.d4rk.portfoliomanager.R
import net.d4rk.portfoliomanager.util.net.api.binance.BinanceApiResponse

class AccountAdapter() :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    var assets = mutableListOf<BinanceApiResponse.Asset>()

    fun setNewAssets(newassets: List<BinanceApiResponse.Asset>) {
        assets = newassets.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val symbolView: TextView
        val priceView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            symbolView = view.findViewById(R.id.symbol)
            priceView = view.findViewById(R.id.price)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_portfolio_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.symbolView.text = assets[position].asset
        viewHolder.priceView.text = assets[position].free
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = assets.size

}
