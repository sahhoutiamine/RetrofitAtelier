package com.example.retrofitatelier

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> get() = _post

    private val _creationSuccess = MutableLiveData<Boolean>()
    val creationSuccess: LiveData<Boolean> get() = _creationSuccess

    fun fetchPosts() {
        viewModelScope.launch {
            val response = repository.getPosts()
            _posts.value = response ?: emptyList()
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            val response = repository.getPostById(postId)
            _post.value = response
        }
    }

    fun createPost(post: Post) {
        viewModelScope.launch {
            val response = repository.createPost(post)
            _creationSuccess.value = response != null
        }
    }
}
