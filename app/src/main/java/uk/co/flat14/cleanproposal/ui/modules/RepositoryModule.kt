package uk.co.flat14.cleanproposal.ui.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import flat14.uk.co.domain.usecase.news.NewsRepository
import uk.co.flat14.data.news.NewsApi

@Module
object RepositoryModule{

    @Provides
    @Reusable
    @JvmStatic
    fun provideNewsRepository(): NewsRepository {
        return NewsApi()
    }
}