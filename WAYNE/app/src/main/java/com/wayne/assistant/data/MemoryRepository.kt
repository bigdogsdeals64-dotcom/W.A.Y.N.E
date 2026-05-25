package com.wayne.assistant.data

class MemoryRepository {
    private val entries = mutableListOf<String>()

    fun addEntry(entry: String) {
        if (entry.isNotBlank()) {
            entries.add(entry)
        }
    }

    fun getEntries(): List<String> = entries.toList()

    fun clearEntries() {
        entries.clear()
    }
}
