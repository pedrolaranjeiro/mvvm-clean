package uk.co.flat14.data.news

import flat14.uk.co.domain.usecase.news.NewsArticleModel
import flat14.uk.co.domain.usecase.news.NewsRepository
import io.reactivex.Single
import mock.MockNews

class NewsApi: NewsRepository{

    override fun getArticlesList(): Single<List<NewsArticleModel>> {
        return MockNews().getNews().map {
            articles -> articles.map(newsMapper)
        }
    }

    private val newsMapper:(NewsDataModel) -> NewsArticleModel = {
        article -> NewsArticleModel(article.id, article.content, article.content, article.author.name, article.creationTime)
    }

}
