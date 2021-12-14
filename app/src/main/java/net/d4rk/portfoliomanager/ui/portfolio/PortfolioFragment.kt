package net.d4rk.portfoliomanager.ui.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import net.d4rk.portfoliomanager.R
import net.d4rk.portfoliomanager.ui.portfolio.adapter.AccountAdapter
import net.d4rk.portfoliomanager.ui.portfolio.adapter.AssetAdapter
import net.d4rk.portfoliomanager.ui.portfolio.repository.AssetRepository
import net.d4rk.portfoliomanager.util.factory.ViewModelFactory
import net.d4rk.portfoliomanager.util.net.api.binance.BinanceApiClient

class PortfolioFragment : Fragment() {

    private lateinit var portfolioViewModel: PortfolioViewModel

//    private lateinit var adapter: LocalAdapter

//    val adapter = AssetAdapter()
    val adapter = AccountAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        portfolioViewModel = ViewModelProvider(this, ViewModelFactory(AssetRepository(
            BinanceApiClient.create())))[PortfolioViewModel::class.java]

        var root = inflater.inflate(R.layout.fragment_portfolio, container, false)

        val assetView: RecyclerView = root.findViewById(R.id.list_asset)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            portfolioViewModel.updateAccountList()
        }

        assetView.adapter = adapter

        portfolioViewModel.mAccountList.observe(viewLifecycleOwner, Observer {
            adapter.setNewAssets(it)
        })

        portfolioViewModel.getTickerList()

        return root
    }

//    sealed class ViewItem(open val id: String, val resource: Int) {
//        data class AlbumItem(override val id: String, val title: String, val childCount: Int) : ViewItem(id, R.layout.layout_portfolio_item)
//    }
//
//    class LocalAdapter(val viewModel: PortfolioViewModel) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(viewType, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.bind(getItem(position))
//        }
//
//
//        inner class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView),
//            LayoutContainer {
//
//            // why update ui here? easier to access view without need to holder.titleTextView
//            fun bind(item: ViewItem) {
//                when(item) {
//                    is ViewItem.AlbumItem -> {
//                        titleTextView.text = item.title
//                        detailTextView.text = "${item.childCount} items"
//                    }
//                }
//            }
//        }
//
//        override fun getItemCount(): Int {
//            TODO("Not yet implemented")
//        }
//
//    }

}