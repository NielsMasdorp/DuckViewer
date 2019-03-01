package nl.ns.duckviewer.presentation.duck.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_ducks.*
import nl.ns.duckviewer.R
import nl.ns.duckviewer.presentation.duck.DuckNavigation
import nl.ns.duckviewer.presentation.duck.DuckViewData
import nl.ns.duckviewer.presentation.duck.details.DuckDetailsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DuckListActivity : AppCompatActivity() {

    private val duckListViewModel: DuckListViewModel by viewModel()

    private lateinit var adapter: DuckAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ducks)
        initList()
        with(duckListViewModel) {
            duckData.observe(this@DuckListActivity, Observer {
                showDucks(it)
            })
            navigateTo.observe(this@DuckListActivity, Observer {
                navigate(it)
            })
        }
    }

    private fun initList() {
        adapter = DuckAdapter().also { adapter ->
            adapter.duckClickListener = { duckListViewModel.onDuckSelected(it.id) }
            duckList.adapter = adapter

        }
    }

    private fun showDucks(ducks: List<DuckViewData>) {
        adapter.ducks = ducks
    }

    // TODO: maybe in router class
    private fun navigate(navigation: DuckNavigation) {
        when(navigation) {
            is DuckNavigation.DuckSelectedNavigation -> navigateToDuckDetails(navigation.id)
        }
    }

    private fun navigateToDuckDetails(id: String) {
        DuckDetailsActivity.startActivity(this, id)
    }
}
