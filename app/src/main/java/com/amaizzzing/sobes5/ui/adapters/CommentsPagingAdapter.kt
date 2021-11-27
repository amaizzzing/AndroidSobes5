package com.amaizzzing.sobes5.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.sobes5.data.services.IImageLoader
import com.amaizzzing.sobes5.databinding.CommentItemBinding
import com.amaizzzing.sobes5.ui.models.CommentModel

class CommentsPagingAdapter(
    private val imageLoader: IImageLoader<ImageView>
): PagingDataAdapter<CommentModel, CommentsPagingAdapter.CommentsViewHolder>(CommentsDiffCallBack()) {
    inner class CommentsViewHolder(
        val binding: CommentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setComment(comment: String) {
            binding.comment.text = comment
        }
        fun setImage(image: String) {
            imageLoader.loadIntoWithRoundCorners(image, binding.imageComment, 14)
        }
        fun setAuthor(author: String) {
            binding.author.text = author
        }
        fun setCountStars(stars: String) {
            binding.countStar.text = stars
        }
        fun setCountComments(countComments: String) {
            binding.countComments.text = countComments
        }
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        getItem(position)?.let {
            with(it) {
                holder.setComment(title)
                holder.setImage(thumbnail)
                holder.setAuthor(author)
                holder.setCountStars(crosspots)
                holder.setCountComments(count_comments)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder =
        CommentsViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun getMovieItem(pos: Int): CommentModel? =
        getItem(pos)
}