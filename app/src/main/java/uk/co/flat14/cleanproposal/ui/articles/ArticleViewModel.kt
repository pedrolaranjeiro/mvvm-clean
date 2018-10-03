package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import uk.co.flat14.data.articles.ArticlesApi
import uk.co.flat14.domain.usecase.articles.ArticleDomainModel
import uk.co.flat14.domain.usecase.articles.GetArticlesUseCase
import java.util.concurrent.TimeUnit

// Based on the code in https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/

open class ArticleViewModel(
        application: Application
) : AndroidViewModel(application) {

    // to be injected
    private val newsRepository = ArticlesApi()
    private val useCase: GetArticlesUseCase = GetArticlesUseCase(newsRepository)
    private lateinit var articlesListLiveData: MutableLiveData<List<ArticleModel>>
    val dataLoading = MutableLiveData<Int>()

    fun loadArticles(): LiveData<List<ArticleModel>> {
        if (!::articlesListLiveData.isInitialized) {
            Log.d("RxCall", "Start")
            dataLoading.value = View.VISIBLE
            articlesListLiveData = MutableLiveData()

            useCase.getArticles()
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

    private val newsMapper: (ArticleDomainModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

}