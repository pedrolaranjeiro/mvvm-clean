package uk.co.flat14.cleanproposal.ui.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.flat14.cleanproposal.R
import uk.co.flat14.cleanproposal.databinding.ActivityArticlesBinding

class ArticlesActivity: AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: ActivityArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_articles)
        binding.articlesListRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        binding.viewModel = viewModel
    }

}