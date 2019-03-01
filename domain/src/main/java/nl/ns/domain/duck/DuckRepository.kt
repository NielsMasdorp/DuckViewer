package nl.ns.domain.duck

import io.reactivex.Single

interface DuckRepository {

    fun getDucks(): Single<List<Duck>>
    fun getDuck(id: String): Single<Duck>
}