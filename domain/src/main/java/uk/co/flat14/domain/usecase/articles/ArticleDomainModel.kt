package uk.co.flat14.domain.usecase.articles

data class ArticleDomainModel(
        val id: String,
        val title: String,
        val content:String,
        val author: String,
        val creationDate:String
)

