package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import uk.co.flat14.data.news.NewsApi
import uk.co.flat14.domain.usecase.news.GetNewsInteractor
import uk.co.flat14.domain.usecase.news.GetNewsUseCase
import uk.co.flat14.domain.usecase.news.NewsArticleModel
import java.util.concurrent.TimeUnit

// Based on the code in https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/

open class ArticleViewModel(
        application: Application
) : AndroidViewModel(application) {

    // to be injected
    private val newsRepository = NewsApi()
    private val useCase: GetNewsUseCase = GetNewsInteractor(newsRepository)
    private lateinit var articlesListLiveData: MutableLiveData<List<ArticleModel>>
    val dataLoading = MutableLiveData<Int>()

    fun loadArticles(): LiveData<List<ArticleModel>> {
        if (!::articlesListLiveData.isInitialized) {
            dataLoading.value = View.VISIBLE
            Log.d("RxCall", "Start")
            articlesListLiveData = MutableLiveData()
            useCase.getNews()
                    .delay(10, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { news -> news.map(newsMapper) }
                    .doAfterTerminate {
                        Log.d("RxCall", "End")
                        dataLoading.value = View.GONE
                    }
                    .subscribe(
                            { onArticleLoadSuccessful(it) },
                            { onArticleLoadError(it) })
        }
        return articlesListLiveData
    }


    private fun onArticleLoadSuccessful(it: List<ArticleModel>) {
        articlesListLiveData.value = it
    }

    private fun onArticleLoadError(it: Throwable) {
        // TODO show some error

    }

    private val newsMapper: (NewsArticleModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

}