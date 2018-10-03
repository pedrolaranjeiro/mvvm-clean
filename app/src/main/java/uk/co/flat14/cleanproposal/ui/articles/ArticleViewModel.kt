package uk.co.flat14.cleanproposal.ui.articles

import android.view.View
import androidx.lifecycle.MutableLiveData
import uk.co.flat14.domain.usecase.news.GetNewsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import uk.co.flat14.cleanproposal.ui.BaseViewModel
import javax.inject.Inject

class ArticleViewModel: BaseViewModel(){

    @Inject
    lateinit var getNewsUseCase: GetNewsUseCase

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadArticles()
    }

    private fun loadArticles(){
        subscription = getNewsUseCase.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{onRetrieveNewsListStart()}
                .doAfterTerminate {
                    onRetrieveNewsListFinish()
                }
                .subscribe(
                    {onRetrieveNewsListSuccess()},
                    {onRetrieveNewsListError()}
                )
    }


    private fun onRetrieveNewsListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveNewsListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveNewsListSuccess(){

    }

    private fun onRetrieveNewsListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}