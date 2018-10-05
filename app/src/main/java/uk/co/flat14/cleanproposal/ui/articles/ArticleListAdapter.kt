package uk.co.flat14.cleanproposal.ui.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_list_row.view.*
import uk.co.flat14.cleanproposal.R

class ArticleListAdapter(private var articleList: List<ArticleModel>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.itemView.title.text = article.title
        holder.itemView.content.text = article.content
        holder.itemView.author.text = article.authorName
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
