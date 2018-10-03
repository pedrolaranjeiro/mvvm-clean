package uk.co.flat14.domain.usecase.articles

import io.reactivex.Single

interface ArticlesRepository {

   fun getArticlesList(): Single<List<ArticleDomainModel>>

}
