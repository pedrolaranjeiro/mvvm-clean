package uk.co.flat14.cleanproposal.ui.articles
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_articles.*
import uk.co.flat14.cleanproposal.R

class ArticlesActivity: AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel
    private var articlesList = ArrayList<ArticleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        articlesListRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesListRecyclerView.adapter = ArticleListAdapter(articlesList)
        articlesListRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        articlesListRecyclerView.setHasFixedSize(true)
        articlesListRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onStart() {
        super.onStart()
        // Live Data
        viewModel.dataLoading.observe(this, Observer {
            loading.visibility = it
        })
        viewModel.loadArticles().observe(this, Observer {
            articlesList.clear()
            articlesList.addAll(it)
        })
    }

}