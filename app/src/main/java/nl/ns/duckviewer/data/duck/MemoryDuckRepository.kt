package nl.ns.duckviewer.data.duck

import io.reactivex.Single
import nl.ns.domain.duck.Duck
import nl.ns.domain.duck.DuckRepository

class MemoryDuckRepository : DuckRepository {

    private val ducksList = listOf(
        Duck(id = "1", name = "Henk", sound = "Quack"),
        Duck(id = "2", name = "Piet", sound = "Quack-Quack"),
        Duck(id = "3", name = "Arie", sound = "Hoi"),
        Duck(id = "4", name = "Johan", sound = "Miep"),
        Duck(id = "5", name = "Chris", sound = "Moooh"),
        Duck(id = "6", name = "Frits", sound = "Hiyaa")
    )

    override fun getDucks(): Single<List<Duck>> {
        return Single.fromCallable { ducksList }
    }

    override fun getDuck(id: String): Single<Duck> {
        return Single.fromCallable {
            ducksList.find { it.id == id }!!
        }
    }
}