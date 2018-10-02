package uk.co.flat14.cleanproposal.ui.modules

import uk.co.flat14.cleanproposal.ui.ui.articles.ArticleViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCasesModule::class ])
interface ViewModelInjector{

    fun inject(articleViewModel: ArticleViewModel)


    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule):Builder

        fun useCaseModule(useCaseModule: UseCasesModule):Builder
    }
}