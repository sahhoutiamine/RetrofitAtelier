package com.example.retrofitatelier

class PostRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun createPost(post: Post): Post? {
        return try {
            apiService.createPost(post)
        } catch (e: Exception) {
            null
        }
    }
}
