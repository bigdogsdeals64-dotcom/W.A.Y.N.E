package com.wayne.assistant.data

class NotesRepository {
    private val notes = mutableListOf<String>()

    fun addNote(value: String) {
        if (value.isNotBlank()) notes.add(value)
    }

    fun getNotes(): List<String> = notes.toList()

    fun clearNotes() {
        notes.clear()
    }
}
