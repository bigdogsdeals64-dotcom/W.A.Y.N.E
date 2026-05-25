package com.wayne.assistant.api

data class GrokMessage(
    val role: String,
    val content: String
)

data class GrokChatRequest(
    val model: String,
    val messages: List<GrokMessage>
)

data class GrokChatResponse(
    val choices: List<GrokChoice> = emptyList()
)

data class GrokChoice(
    val message: GrokMessage? = null
)
