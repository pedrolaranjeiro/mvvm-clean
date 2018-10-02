package flat14.uk.co.domain.usecase.news

import io.reactivex.Single

interface NewsRepository {

   fun getArticlesList(): Single<List<NewsArticleModel>>

}
