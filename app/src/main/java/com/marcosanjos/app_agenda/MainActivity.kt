package com.marcosanjos.app_agenda

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosanjos.app_agenda.adapter.ItemAdapter
import com.marcosanjos.app_agenda.databinding.ActivityMainBinding
import com.marcosanjos.app_agenda.model.Item
import com.marcosanjos.app_agenda.service.RetrofitClient
import com.marcosanjos.app_agenda.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.marcosanjos.app_agenda.service.Result


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchItems()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchItems()
    }

    private fun fetchItems() {
        binding.swipeRefreshLayout.isRefreshing = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = safeApiCall {
                RetrofitClient.apiService.getItems()
            }

            withContext(Dispatchers.Main) {
                binding.swipeRefreshLayout.isRefreshing = false
                when (result) {
                    is Result.Success -> {
                        handleOnSuccess(result.data)
                    }
                    is Result.Error -> {
                        // Handle error (e.g., show a Toast)
                    }
                }
            }
        }
    }

    private fun handleOnSuccess(items: List<Item>) {
        binding.recyclerView.adapter = ItemAdapter(items) {
            item -> showItemDetails(item) // Handle item click
        }
    }

    private fun showItemDetails(item: Item) {
        Toast.makeText(this, "Clicked on ${item.value.fullName}", Toast.LENGTH_SHORT).show()
    }
}
