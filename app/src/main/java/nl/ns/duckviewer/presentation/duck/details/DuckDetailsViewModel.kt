package nl.ns.duckviewer.presentation.duck.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.ns.domain.duck.GetDuck
import nl.ns.duckviewer.presentation.duck.DuckViewData

class DuckDetailsViewModel(
    private val getDuck: GetDuck,
    private val selectedDuckId: String
) : ViewModel() {

    private var disposable: Disposable? = null

    val duckData = MutableLiveData<DuckViewData>()

    init {
        fetchDuck()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    private fun fetchDuck() {
        disposable = getDuck.execute(selectedDuckId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { DuckViewData(it.id, it.name, it.sound) }
            .subscribe { duck -> duckData.postValue(duck) }
    }
}