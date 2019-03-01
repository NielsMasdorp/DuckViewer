package nl.ns.duckviewer.presentation.duck

import nl.ns.domain.duck.GetDuck
import nl.ns.domain.duck.GetDucks
import nl.ns.duckviewer.presentation.duck.details.DuckDetailsActivity
import nl.ns.duckviewer.presentation.duck.details.DuckDetailsViewModel
import nl.ns.duckviewer.presentation.duck.list.DuckListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val duckModule = module {

    viewModel { DuckDetailsViewModel(get(), getProperty(DuckDetailsActivity.KEY_DUCK_ID)) }
    viewModel { DuckListViewModel(get()) }
    factory { GetDuck(get()) }
    factory { GetDucks(get()) }
}