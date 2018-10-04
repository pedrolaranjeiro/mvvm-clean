package uk.co.flat14.cleanproposal.ui.articles

import android.app.Application
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class ArticleViewModelTest{

    private val mockApp = mock<Application>{}
    private val viewModel = ArticleViewModel(mockApp)

    @Test
    fun onViewModelCreationAdapterIsCreated(){

    }

    @Test
    fun onGetArticlesSuccessAddItemsToList(){
        //Given

        //When
        viewModel.update(any())

        //Then

    }

    @Test
    fun onGetArticlesErrorTriggerShowError(){
        //Given

        //When
        viewModel.update(any())


        //Then
    }

}