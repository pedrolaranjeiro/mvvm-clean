package uk.co.flat14.domain.usecase.articles

import io.reactivex.Single

class GetArticlesUseCase(private val articlesRepository: ArticlesRepository){

    fun getNews(): Single<List<ArticleDomainModel>>{
        return articlesRepository.getArticlesList()
    }

}