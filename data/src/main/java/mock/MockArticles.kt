package mock

import uk.co.flat14.data.articles.model.ArticleDataModel
import uk.co.flat14.data.articles.model.AuthorDataModel

class MockArticles{

    fun getArticles(): List<ArticleDataModel> {
        val author = AuthorDataModel("a-123", "John Doe", "2018-08-03 9h33:00.000")
        val articlesList = ArrayList<ArticleDataModel>()

        articlesList.add(
                ArticleDataModel(
                        "1",
                        "Doe wins Moto GP2",
                        "Doe gets a fantastic victory in an epic Race last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )

        articlesList.add(
                ArticleDataModel(
                        "2",
                        "Doe does it again",
                        "Doe gets another fantastic victory in an epic Race last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )


        articlesList.add(
                ArticleDataModel(
                        "1",
                        "Hat Trick for Doe",
                        "Again? That's right Doe is unstoppable. A fastastic victory last saturday",
                        author,
                        "2018-08-03 9h33:00.000")
        )

//        if(Random().nextBoolean()){
//            throw DataNotAvailableException()
//        }

        return articlesList
    }

}