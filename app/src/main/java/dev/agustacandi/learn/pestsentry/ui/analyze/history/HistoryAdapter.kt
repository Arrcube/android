package dev.agustacandi.learn.pestsentry.ui.analyze.history

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoriesItem
import dev.agustacandi.learn.pestsentry.databinding.ItemHistoryBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.formatDate
import dev.agustacandi.learn.pestsentry.utils.ext.showConfirmDialog

class HistoryAdapter(private val fragment: HistoryFragment, private val historyViewModel: HistoryViewModel) : ListAdapter<HistoriesItem, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemHistoryBinding, private val fragment: HistoryFragment, private val historyViewModel: HistoryViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoriesItem) {
            with(binding) {
                historyImage.load(history.imageUrl) {
                    placeholder(ColorDrawable(Color.LTGRAY))
                    transformations(RoundedCornersTransformation(16f))
                }
                historyTitle.text = history.prediction
                historyDate.text = history.createdAt.formatDate()
                root.setOnClickListener { view ->
                    val navigateToDetailArticle =
                        HistoryFragmentDirections.actionHistoryFragmentToResultFragment(
                            history.prediction, history.imageUrl, root.context.getString(
                                R.string.history_menu
                            )
                        )
                    view.findNavController().navigate(navigateToDetailArticle)
                }
                deleteHistoryButton.setOnClickListener {
                   fragment.showConfirmDialog(
                       title = fragment.getString(R.string.are_you_sure),
                       message = "",
                       positiveButtonText = fragment.getString(R.string.yes),
                       negativeButtonText = fragment.getString(R.string.no),
                       onPositiveClick = {
                           historyViewModel.deleteHistoryById(history.historyId)
                       }
                   )
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, fragment, historyViewModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoriesItem>() {
            override fun areItemsTheSame(oldItem: HistoriesItem, newItem: HistoriesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoriesItem, newItem: HistoriesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}