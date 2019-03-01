package nl.ns.domain.duck

import io.reactivex.Single

class GetDucks(private val duckRepository: DuckRepository) {

    fun execute(): Single<List<Duck>> = duckRepository.getDucks()
}