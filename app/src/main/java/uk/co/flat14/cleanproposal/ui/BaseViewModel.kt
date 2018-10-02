package uk.co.flat14.cleanproposal.ui

import androidx.lifecycle.ViewModel
import uk.co.flat14.cleanproposal.ui.modules.DaggerViewModelInjector
import uk.co.flat14.cleanproposal.ui.modules.RepositoryModule
import uk.co.flat14.cleanproposal.ui.modules.UseCasesModule
import uk.co.flat14.cleanproposal.ui.modules.ViewModelInjector
import uk.co.flat14.cleanproposal.ui.ui.articles.ArticleViewModel

abstract class BaseViewModel: ViewModel(){

    private val injector : ViewModelInjector = DaggerViewModelInjector
            .builder()
            .repositoryModule(RepositoryModule)
            .useCaseModule(UseCasesModule)
            .build()

    init{
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ArticleViewModel -> injector.inject(this)
        }
    }

}