package uk.co.flat14.cleanproposal.ui.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import flat14.uk.co.domain.usecase.news.GetNewsInteractor
import flat14.uk.co.domain.usecase.news.GetNewsUseCase
import flat14.uk.co.domain.usecase.news.NewsRepository

@Module
object UseCasesModule{

    @Provides
    @Reusable
    @JvmStatic
    fun provideGetNewsUseCase(newsRepository: NewsRepository):GetNewsUseCase{
        return GetNewsInteractor(newsRepository)
    }
}
