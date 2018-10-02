package flat14.uk.co.domain.usecase.news

import io.reactivex.Single

interface GetNewsUseCase{

    fun getNews(): Single<List<NewsArticleModel>>

}