package com.example.retrofitatelier

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val etPostId = findViewById<EditText>(R.id.etPostId)
        val tvPostDetails = findViewById<TextView>(R.id.tvPostDetails)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        viewModel.posts.observe(this) { posts ->
            recyclerView.adapter = PostAdapter(posts)
        }

        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un ID valide."
            }
        }

        viewModel.post.observe(this) { post ->
            if (post != null) {
                tvPostDetails.text = "ID: ${post.id}\nTitre: ${post.title}\nContenu: ${post.body}"
            } else {
                tvPostDetails.text = "Aucun post trouvé."
            }
        }
    }
}
