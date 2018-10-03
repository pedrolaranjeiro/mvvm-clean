package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import uk.co.flat14.data.articles.ArticlesApi
import uk.co.flat14.domain.usecase.articles.ArticleDomainModel
import uk.co.flat14.domain.usecase.articles.GetArticlesUseCase

// Based on the code in https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/

open class ArticleViewModel(
        application: Application
) : AndroidViewModel(application) {

    // to be injected
    private val newsRepository = ArticlesApi()
    private val useCase: GetArticlesUseCase = GetArticlesUseCase(newsRepository)

    lateinit var articlesData: MutableLiveData<List<ArticleModel>>
    val dataLoading = MutableLiveData<Int>()
    val showErrorMessage = MutableLiveData<Boolean>()

    init {
        dataLoading.value = View.GONE
        showErrorMessage.value = false
    }

    fun loadArticles(): MutableLiveData<List<ArticleModel>> {
        if (!::articlesData.isInitialized) {
            articlesData = MutableLiveData()
            fetchArticles()
        }
        return articlesData
    }

    private fun fetchArticles(){
        dataLoading.value = View.VISIBLE
        Log.d("RxCall", "Start")
        useCase.getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .map { news ->
                    news.map(newsMapper)
                }
                .doAfterTerminate {
                    Log.d("RxCall", "End")
                    dataLoading.value = View.GONE
                }
                .subscribe(
                        { onArticleLoadSuccessful(it) },
                        { onArticleLoadError(it) })
    }

    private fun onArticleLoadSuccessful(it: List<ArticleModel>) {
        articlesData.value = it
    }

    private fun onArticleLoadError(it: Throwable) {
        // Clear list
        articlesData.value = ArrayList()
        showErrorMessage.value = true
    }

    private val newsMapper: (ArticleDomainModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

}