package nl.ns.duckviewer.presentation.duck.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.ns.domain.duck.GetDucks
import nl.ns.duckviewer.generic.livedata.SingleLiveEvent
import nl.ns.duckviewer.presentation.duck.DuckNavigation
import nl.ns.duckviewer.presentation.duck.DuckViewData

class DuckListViewModel(private val getDucks: GetDucks) : ViewModel() {

    private var disposable: Disposable? = null

    val navigateTo = SingleLiveEvent<DuckNavigation>()

    val duckData = MutableLiveData<List<DuckViewData>>()

    init {
        fetchDucks()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    private fun fetchDucks() {
        disposable = getDucks.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.map { duck -> DuckViewData(duck.id, duck.name, duck.sound) } }
            .subscribe { ducks -> duckData.postValue(ducks) }
    }

    fun onDuckSelected(id: String) {
        navigateTo.postValue(DuckNavigation.DuckSelectedNavigation(id))
    }
}