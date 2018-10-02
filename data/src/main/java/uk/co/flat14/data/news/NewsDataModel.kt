package uk.co.flat14.data.news

data class NewsDataModel(val id: String,
                         val title: String,
                         val content:String,
                         val author: AuthorDataModel,
                         val creationTime:String)
