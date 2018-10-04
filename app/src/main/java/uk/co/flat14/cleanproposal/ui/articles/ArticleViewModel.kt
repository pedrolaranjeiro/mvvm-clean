package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
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

    private lateinit var articles: MutableLiveData<List<ArticleModel>>

    // used to control the visibility of the loading view.
    val dataLoading = ObservableInt()
    lateinit var adapter : ArticleListAdapter


//    val showErrorMessage = MutableLiveData<Boolean>()

    init {
        dataLoading.set(View.GONE)
        loadArticles()
//        showErrorMessage.value = false
    }

    private fun loadArticles(): MutableLiveData<List<ArticleModel>> {
        if (!::articles.isInitialized) {
            articles = MutableLiveData()
            adapter = ArticleListAdapter(articles)
            fetchArticles()
        }
        return articles
    }

    private fun fetchArticles(){
        dataLoading.set(View.VISIBLE)
        Log.d("RxCall", "Start")
        useCase.getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .map { news ->
                    news.map(newsMapper)
                }
                .doAfterTerminate {
                    Log.d("RxCall", "End")
                    dataLoading.set(View.GONE)
                }
                .subscribe(
                        { onArticleLoadSuccessful(it) },
                        { onArticleLoadError(it) })
    }

    private fun onArticleLoadSuccessful(it: List<ArticleModel>) {
        articles.value = it
    }

    private fun onArticleLoadError(it: Throwable) {
        // Clear list
//        articlesData.value = ArrayList()
//        showErrorMessage.value = true
    }

    private val newsMapper: (ArticleDomainModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

}