package uk.co.flat14.cleanproposal.ui.articles
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_articles.*
import uk.co.flat14.cleanproposal.R

class ArticlesActivity: AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        articlesListRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesListRecyclerView.adapter = ArticleListAdapter(viewModel.items)
        articlesListRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        articlesListRecyclerView.setHasFixedSize(true)
        articlesListRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadArticles()
        viewModel.dataLoading
    }

}