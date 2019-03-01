package nl.ns.duckviewer.presentation.duck.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_duck_details.*
import nl.ns.duckviewer.R
import nl.ns.duckviewer.presentation.duck.DuckViewData
import org.koin.android.ext.android.setProperty
import org.koin.android.viewmodel.ext.android.viewModel

class DuckDetailsActivity : AppCompatActivity() {

    private val duckDetailsViewModel: DuckDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duck_details)
        setProperty(KEY_DUCK_ID, intent.getStringExtra(KEY_DUCK_ID))
        duckDetailsViewModel.duckData.observe(this@DuckDetailsActivity, Observer {
            showDuck(it)
        })
    }

    private fun showDuck(duckViewData: DuckViewData) {
        duckDetails.text = "This duck is called: ${duckViewData.name} and his sound is: ${duckViewData.sound}"
    }

    companion object {

        const val KEY_DUCK_ID = "KEY_DUCK_ID"

        fun startActivity(context: Context, selectedDuckId: String) {
            context.startActivity(Intent(context, DuckDetailsActivity::class.java).apply {
                putExtra(KEY_DUCK_ID, selectedDuckId)
            })
        }
    }
}