package flat14.uk.co.domain.usecase.news

import io.reactivex.Single

class GetNewsInteractor(val newsRepository: NewsRepository): GetNewsUseCase{

    override fun getNews(): Single<List<NewsArticleModel>> {
        return newsRepository.getArticlesList()
    }

}