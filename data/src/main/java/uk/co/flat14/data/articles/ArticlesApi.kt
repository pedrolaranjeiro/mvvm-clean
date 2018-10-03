package uk.co.flat14.data.articles

import uk.co.flat14.domain.usecase.articles.ArticlesRepository
import io.reactivex.Single
import mock.MockArticles
import uk.co.flat14.domain.usecase.articles.ArticleDomainModel

class ArticlesApi: ArticlesRepository {

    override fun getArticlesList(): Single<List<ArticleDomainModel>> {
        return MockArticles().getArticles().map {
            articles -> articles.map(mapperDomain)
        }
    }

    private val mapperDomain:(ArticleDataModel) -> ArticleDomainModel = {
        article ->
        ArticleDomainModel(article.id, article.title, article.content, article.author.name, article.creationTime)
    }

}
