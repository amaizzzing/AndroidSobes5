package com.amaizzzing.sobes5.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.amaizzzing.sobes5.ui.models.CommentModel

class CommentsDiffCallBack : DiffUtil.ItemCallback<CommentModel>() {
    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem.title == newItem.title && oldItem.author == oldItem.author
    }

    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }
}