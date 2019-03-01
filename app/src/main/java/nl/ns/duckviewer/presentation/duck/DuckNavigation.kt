package nl.ns.duckviewer.presentation.duck

sealed class DuckNavigation {

    data class DuckSelectedNavigation(val id: String): DuckNavigation()
}