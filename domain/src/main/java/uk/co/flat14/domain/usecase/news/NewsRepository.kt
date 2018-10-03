package uk.co.flat14.domain.usecase.news

import io.reactivex.Single

interface NewsRepository {

   fun getArticlesList(): Single<List<NewsArticleModel>>

}
