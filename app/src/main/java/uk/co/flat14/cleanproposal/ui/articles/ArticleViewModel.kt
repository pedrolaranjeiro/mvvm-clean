package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.flat14.data.news.NewsApi
import uk.co.flat14.domain.usecase.news.GetNewsInteractor
import uk.co.flat14.domain.usecase.news.GetNewsUseCase
import uk.co.flat14.domain.usecase.news.NewsArticleModel

// Based on the code in https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/

open class ArticleViewModel(
        application: Application
): AndroidViewModel(application){

    // to be injected
    private val newsRepository = NewsApi()
    private val useCase: GetNewsUseCase = GetNewsInteractor(newsRepository)

//    private val context: Context = application.applicationContext //Application Context to avoid leaks.
    val items: ObservableList<ArticleModel> = ObservableArrayList()
    val dataLoading = ObservableBoolean(false)

    fun loadArticles(){
        dataLoading.set(true)
        useCase.getNews()
                .map {news -> news.map(newsMapper)}
                .doFinally { dataLoading.set(false)}
                .subscribe (
                        {onArticleLoadSuccessful(it)},
                        {onArticleLoadError(it)} )
    }


    private fun onArticleLoadSuccessful(it: List<ArticleModel>) {
        with(items){
            clear()
            addAll(it)
        }
    }

    private fun onArticleLoadError(it: Throwable) {
        // TODO show some error

    }

    private val newsMapper:(NewsArticleModel) -> ArticleModel = {
        article -> ArticleModel(article.title, article.content, article.author)
    }



}