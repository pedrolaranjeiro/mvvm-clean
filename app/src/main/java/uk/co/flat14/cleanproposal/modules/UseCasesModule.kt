package uk.co.flat14.cleanproposal.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import uk.co.flat14.domain.usecase.news.GetNewsInteractor
import uk.co.flat14.domain.usecase.news.GetNewsUseCase
import uk.co.flat14.domain.usecase.news.NewsRepository

@Module
object UseCasesModule{

    @Provides
    @Reusable
    @JvmStatic
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsInteractor(newsRepository)
    }
}
