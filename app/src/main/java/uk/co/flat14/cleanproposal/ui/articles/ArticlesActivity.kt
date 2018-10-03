package uk.co.flat14.cleanproposal.ui.articles

import android.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import uk.co.flat14.cleanproposal.R
import uk.co.flat14.cleanproposal.databinding.ActivityArticlesBinding

class ArticlesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityArticlesBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_articles)
        binding.articlesListRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        binding.viewModel = viewModel
    }

}