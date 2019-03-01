package nl.ns.duckviewer

import android.app.Application
import nl.ns.domain.duck.DuckRepository
import nl.ns.duckviewer.data.duck.MemoryDuckRepository
import nl.ns.duckviewer.presentation.duck.duckModule
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class TestApplication : Application() {

    private val appModule = module {
        factory { MemoryDuckRepository() as DuckRepository }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, duckModule))
    }
}