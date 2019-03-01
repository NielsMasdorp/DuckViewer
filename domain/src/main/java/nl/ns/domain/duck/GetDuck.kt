package nl.ns.domain.duck

import io.reactivex.Single

class GetDuck(private val duckRepository: DuckRepository) {

    fun execute(id: String): Single<Duck> = duckRepository.getDuck(id)
}