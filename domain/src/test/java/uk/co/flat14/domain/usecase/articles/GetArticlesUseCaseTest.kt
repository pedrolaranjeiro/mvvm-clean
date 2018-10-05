package uk.co.flat14.domain.usecase.articles

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Test
import uk.co.flat14.domain.usecase.exception.DataNotAvailableException
import java

class GetArticlesUseCaseTest {

    @Test
    fun getArticlesReturnSuccess() {
        // Given
        val expectedItems: List<ArticleDomainModel> = Arrays.asList(
                ArticleDomainModel("1", "title1", "content1", "author1", "date1"),
                ArticleDomainModel("2", "title2", "content2", "author2", "date2"),
                ArticleDomainModel("3", "title3", "content3", "author3", "date3")
        )
        val mockRepository = mock<ArticlesRepository> {
            on { getArticlesList() } doReturn Single.just(expectedItems)
        }
        val useCase = GetArticlesUseCase(mockRepository)

        // When
        useCase.getArticles()
                .test()
                .assertResult(expectedItems)

        // Then
        verify(mockRepository, atLeastOnce()).getArticlesList()
    }

    @Test(expected = DataNotAvailableException::class)
    fun getArticlesThrowsError() {
        // Given
        val mockRepository = mock<ArticlesRepository> {
            on { getArticlesList() } doAnswer { _ ->  throw DataNotAvailableException()}
        }
        val useCase = GetArticlesUseCase(mockRepository)

        // When
        useCase.getArticles()
                .test()
    }

}