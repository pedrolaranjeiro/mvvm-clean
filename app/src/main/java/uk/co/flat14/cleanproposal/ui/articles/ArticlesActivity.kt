package uk.co.flat14.cleanproposal.ui.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_articles.*
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

    override fun onStart() {
        super.onStart()

        // Live Data trigger Snackbar
        viewModel.showErrorMessage.observe(this, Observer { showError ->
            if(showError){
                Snackbar.make(rootView, R.string.error_server_unavailable, Snackbar.LENGTH_LONG).show()
            }
        })
    }

}