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
open class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    // to be injected
    val newsRepository = ArticlesApi()
    val useCase: GetArticlesUseCase = GetArticlesUseCase(newsRepository)

    // Other vars
    private lateinit var articlesList: ArrayList<ArticleModel>
    private lateinit var subscription: Disposable

    // Used to trigger events in the activity
    val showErrorMessage = MutableLiveData<Boolean>()

    // Used in the XML
    val dataLoading = ObservableInt()
    lateinit var adapter: ArticleListAdapter

    init {
        dataLoading.set(View.GONE)
        loadArticles()
        showErrorMessage.value = false
    }
    // Load data
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
                .map { news -> news.map(newsMapper) }
                .doAfterTerminate {
                    Log.d("RxCall", "End")
                    dataLoading.set(View.GONE)
                }
                .subscribe({ onSuccess(it) }, { onError() })
    }

    private fun onSuccess(it: List<ArticleModel>) {
        articlesList.clear()
        articlesList.addAll(it)
        adapter.notifyDataSetChanged()
    }

    private fun onError() {
        articlesList.clear()
        showErrorMessage.value = true
    }

    // onClickEvent
    fun update(view: View) {
        fetchArticles()
    }

    // Map data to presentation layer model
    private val newsMapper: (ArticleDomainModel) -> ArticleModel = { article ->
        ArticleModel(article.title, article.content, article.author)
    }

    // Clean Resources
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}