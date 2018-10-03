package uk.co.flat14.data.news

import io.reactivex.Scheduler
import uk.co.flat14.domain.usecase.news.NewsArticleModel
import uk.co.flat14.domain.usecase.news.NewsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import mock.MockNews
import java.util.concurrent.TimeUnit

class NewsApi: NewsRepository {

    override fun getArticlesList(): Single<List<NewsArticleModel>> {
        return MockNews().getNews().map {
            articles -> articles.map(newsMapper)
        }
    }

    private val newsMapper:(NewsDataModel) -> NewsArticleModel = {
        article ->
        NewsArticleModel(article.id, article.title, article.content, article.author.name, article.creationTime)
    }

}
