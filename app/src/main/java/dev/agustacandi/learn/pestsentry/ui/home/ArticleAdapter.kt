package dev.agustacandi.learn.pestsentry.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater.*
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import dev.agustacandi.learn.pestsentry.data.news.dto.ArticlesItem
import dev.agustacandi.learn.pestsentry.databinding.ItemArticleBinding

class ArticleAdapter(private val navDirections: (String) -> NavDirections) : ListAdapter<ArticlesItem, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemArticleBinding, private val navDirections: (String) -> NavDirections) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem) {
            with(binding) {
                articleImage.load(article.urlToImage) {
                    placeholder(ColorDrawable(Color.LTGRAY))
                    transformations(RoundedCornersTransformation(16f))
                }
                articleTitle.text = article.title
                articleDescription.text = article.description
                root.setOnClickListener { view ->
                    val navigateToDetailArticle = navDirections(article.url)
                    view.findNavController().navigate(navigateToDetailArticle)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemArticleBinding.inflate(from(parent.context), parent, false)
        return MyViewHolder(binding, navDirections)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}