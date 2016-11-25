package ruliov.obliviate.controllers

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import ruliov.data.mapR
import ruliov.jetty.createControllerRespondsJSON
import ruliov.jetty.createControllerRespondsJSONDontCaches
import ruliov.obliviate.LOG
import ruliov.obliviate.db.Database
import ruliov.obliviate.json.toCompactJSON
import ruliov.obliviate.json.toJSON
import java.io.InputStream

fun getAllWordsController(database: Database) = createControllerRespondsJSON { request, groups ->
    val words = database.getAllWords()

    request.response.writer.write(words.toCompactJSON())
}

private fun getRandomWordJSON(database: Database): String {
    val word = database.getRandomWordWith4RandomTranslations()

    return word?.toJSON() ?: "{\"error\":\"no-words\"}"
}

fun getRandomWordController(database: Database) = createControllerRespondsJSONDontCaches { request, strings ->
    request.response.writer.write(getRandomWordJSON(database))
}

fun checkAndGetNextWordController(database: Database) = createControllerRespondsJSON { request, groups ->
    groups ?: throw IllegalStateException()

    val wordId = groups[0].toLong()
    val translationIdJSON = database.getWordTranslationId(wordId)?.let { "\"$it\"" } ?: "null"

    val nextWordJSON = getRandomWordJSON(database)

    request.response.writer.write("{\"correct\":$translationIdJSON,\"word\":$nextWordJSON}")
}

fun deleteWordController(database: Database) = createControllerRespondsJSON { request, groups ->
    groups ?: throw IllegalStateException()

    val wordId = groups[0].toLong()

    val asyncContext = request.startAsync()

    database.deleteWord(wordId).run {
        if (it == null) {
            request.response.writer.write("{\"error\":null}")
        } else {
            LOG.error("Word deletion:", it)
            respond500(request)
        }

        asyncContext.complete()
    }
}

fun updateWordController(database: Database) = createControllerRespondsJSON { request, groups ->
    groups ?: throw IllegalStateException()

    val wordId = groups[0].toLong()

    val maybeParsed = extractTwoStringsFromJSONArray(request.inputStream)
    if (maybeParsed == null) {
        request.response.status = 400
        return@createControllerRespondsJSON
    }

    val word = maybeParsed.first
    val translation = maybeParsed.second

    val asyncContext = request.startAsync()

    database.updateWord(wordId, word, translation).run {
        if (it == null) {
            request.response.writer.write("{\"error\":null}")
        } else {
            LOG.error("Word update:", it)
            respond500(request)
        }

        asyncContext.complete()
    }
}

fun createWordController(database: Database) = createControllerRespondsJSON { request, groups ->
    val maybeParsed = extractTwoStringsFromJSONArray(request.inputStream)
    if (maybeParsed == null) {
        request.response.status = 400
        return@createControllerRespondsJSON
    }

    val word = maybeParsed.first
    val translation = maybeParsed.second

    val asyncContext = request.startAsync()

    database.createWord(word, translation).run { it.mapR({
        request.response.writer.write("{\"error\":null,\"id\":$it}")

        asyncContext.complete()
    }, {
        if (it is Database.WordValidationError) {
            request.response.status = 400
            request.response.writer.write("{\"error\":\"validation\"}")
        } else {
            request.response.status = 500
            request.response.writer.write("{\"error\":\"server\"}")
            LOG.error("Word creation error:", it)
        }

        asyncContext.complete()
    }) }
}

private fun extractTwoStringsFromJSONArray(stream: InputStream): Pair<String, String>? {
    val jsonTokener = JSONTokener(stream)

    val body: JSONArray
    try {
        body = JSONArray(jsonTokener)
    } catch (ex: JSONException) {
        return null
    }

    if (body.length() < 2) {
        return null
    }

    val word = body.getString(0)
    val translation = body.getString(1)

    return Pair(word, translation)
}