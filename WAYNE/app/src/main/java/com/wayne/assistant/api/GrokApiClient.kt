package com.wayne.assistant.api

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class GrokApiClient {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    fun buildChatRequest(apiKey: String, prompt: String): Request {
        val payload = mapOf(
            "model" to "grok-beta",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "You are W.A.Y.N.E., a helpful voice assistant."),
                mapOf("role" to "user", "content" to prompt)
            )
        )

        return Request.Builder()
            .url("https://api.x.ai/v1/chat/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .post(gson.toJson(payload).toRequestBody(jsonMediaType))
            .build()
    }
}
