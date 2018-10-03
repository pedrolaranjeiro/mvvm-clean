package uk.co.flat14.data.articles

data class ArticleDataModel(val id: String,
                            val title: String,
                            val content:String,
                            val author: AuthorDataModel,
                            val creationTime:String)
