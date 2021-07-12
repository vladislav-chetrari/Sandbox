package io.sandbox.app.main.routes.characters

class CharacterSearchCriteria(name: String? = null, status: String? = null) {
    val name: String
    val status: String

    init {
        this.name = name ?: ""
        this.status = status ?: ""
    }
}