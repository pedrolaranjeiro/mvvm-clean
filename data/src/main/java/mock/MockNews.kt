package mock

import io.reactivex.Single
import uk.co.flat14.data.news.AuthorDataModel
import uk.co.flat14.data.news.NewsDataModel
import java.util.*
import kotlin.collections.ArrayList

class MockNews{

    fun getNews(): Single<List<NewsDataModel>> {
        val author = AuthorDataModel("a-123", "John Doe", "2018-08-03 9h33:00.000")
        val articlesList = ArrayList<NewsDataModel>()

        articlesList.add(
                NewsDataModel(
                        "1",
                        "Doe wins Moto GP2",
                        "Doe gets a fantastic victory in an epic Race last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )

        articlesList.add(
                NewsDataModel(
                        "2",
                        "Doe does it again",
                        "Doe gets another fantastic victory in an epic Race last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )


        articlesList.add(
                NewsDataModel(
                        "1",
                        "Hat Trick for Doe",
                        "Again? That's right Doe is unstoppable. A fastastic victory last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )

        Thread.sleep(5000)
        return Single.just(articlesList)
    }

}