package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import uk.co.flat14.data.articles.ArticlesApi
import uk.co.flat14.domain.usecase.articles.ArticleDomainModel
import uk.co.flat14.domain.usecase.articles.GetArticlesUseCase

// Based on the code in https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/
class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    // to be injected
    private val newsRepository = ArticlesApi()
    private val useCase: GetArticlesUseCase = GetArticlesUseCase(newsRepository)

    // Used in the XML
    val dataLoading = ObservableInt()
    lateinit var adapter: ArticleListAdapter

    // Other vars
    private lateinit var articlesList: ArrayList<ArticleModel>
    private lateinit var subscription: Disposable
    val showErrorMessage = MutableLiveData<Boolean>()

    init {
        dataLoading.set(View.GONE)
        loadArticles()
        showErrorMessage.value = false
    }

    private fun loadArticles() {
        if (!::articlesList.isInitialized) {
            articlesList = ArrayList()
            adapter = ArticleListAdapter(articlesList)
            fetchArticles()
        }
    }

    private fun fetchArticles() {
        dataLoading.set(View.VISIBLE)
        Log.d("RxCall", "Start")
        subscription = useCase.getArticles()
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
        articlesList.clear()
        articlesList.addAll(it)
        adapter.notifyDataSetChanged()
    }

    private fun onArticleLoadError(it: Throwable) {
        // Clear list
        articlesList.clear()
        adapter.notifyDataSetChanged()
        showErrorMessage.value = true
    }

    private val newsMapper: (ArticleDomainModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}