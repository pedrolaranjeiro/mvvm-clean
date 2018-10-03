package uk.co.flat14.domain.usecase.news

import io.reactivex.Single

interface GetNewsUseCase{

    fun getNews(): Single<List<NewsArticleModel>>

}