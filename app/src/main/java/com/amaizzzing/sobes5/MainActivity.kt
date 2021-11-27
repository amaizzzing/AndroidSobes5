package com.amaizzzing.sobes5

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.sobes5.data.services.IImageLoader
import com.amaizzzing.sobes5.databinding.ActivityMainBinding
import com.amaizzzing.sobes5.di.ViewModelFactory
import com.amaizzzing.sobes5.ui.BaseState
import com.amaizzzing.sobes5.ui.adapters.CommentsPagingAdapter
import com.amaizzzing.sobes5.ui.models.CommentModel
import com.amaizzzing.sobes5.ui.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    private var binding: ActivityMainBinding? = null
    private lateinit var mainViewModel: MainViewModel
    private var adapter: CommentsPagingAdapter? = null

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        RedditApp.redditComponent.inject(this)

        mainViewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[MainViewModel::class.java]

        observeData()
    }

    private fun observeData() {
        mainViewModel.data.observe(this) {
            it?.let {
                renderData(it)
            } ?: renderData(BaseState.Error(Error()))
        }

        initRecyclerView()

        mainViewModel.getTopComments()
    }

    private fun initRecyclerView() {
        binding?.let {
            it.rvComments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            adapter = CommentsPagingAdapter(imageLoader)
            it.rvComments.adapter = adapter
        }
    }

    private fun renderData(data: BaseState) {
        when(data) {
            is BaseState.Success<*> -> {
                isProgressVisible(false)
                (data.resultData as PagingData<CommentModel>).also {
                    launch(Dispatchers.Main) {
                        adapter?.submitData(it)
                    }
                }
            }
            is BaseState.Error -> {
                isProgressVisible(false)
                Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
            }
            BaseState.Loading -> {
                isProgressVisible(true)
            }
        }
    }

    private fun isProgressVisible(isVisible: Boolean) {
        binding?.let {
            if (isVisible) {
                it.rvComments.visibility = View.GONE
                it.pbMain.visibility = View.VISIBLE
            } else {
                it.rvComments.visibility = View.VISIBLE
                it.pbMain.visibility = View.GONE
            }
        }
    }
}