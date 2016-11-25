@file:JvmName("Main")

package ruliov.obliviate

import org.eclipse.jetty.server.Request
import ruliov.async.bindAnyErrorFuture
import ruliov.async.createFuture
import ruliov.jetty.HTTPRouter
import ruliov.jetty.IHTTPMiddleware
import ruliov.jetty.JettyServer
import ruliov.jetty.static.DevelopmentStaticFilesServer
import ruliov.jetty.static.StaticFilesServer
import ruliov.obliviate.auth.AuthProvider
import ruliov.obliviate.controllers.*
import ruliov.obliviate.db.Database
import ruliov.toJDBCUrl
import java.io.File
import java.util.regex.Pattern

val LOCAL: Boolean = System.getenv("LOCAL") != null
val PRODUCTION: Boolean = !LOCAL

val OUR_URI = if (PRODUCTION)
    "https://obliviate-332.herokuapp.com" else
    "http://localhost:5001"

val JDBC_DATABASE_URL =
    "jdbc:" + toJDBCUrl(System.getenv("JDBC_DATABASE_URL") ?:
                        "postgres://ruliov:ruliov@localhost:5432/obliviate1")

val JDBC_DRIVER = "org.postgresql.Driver"
fun initJDBCDriver() {
    Class.forName(JDBC_DRIVER)
}

val PORT = System.getenv("PORT")?.toInt() ?: 5000

val VK_SECRET: String = System.getenv("VK_SECRET") ?: throw IllegalStateException("No VK_SECRET env")

fun main(args: Array<String>) {
    initJDBCDriver()

    val database = Database(JDBC_DATABASE_URL)

    val authProvider = AuthProvider(database)

    database.loadData().bindAnyErrorFuture {
        val classLoader = ClassLoader.getSystemClassLoader()

        val staticFilesServer = if (PRODUCTION)
            StaticFilesServer({ s ->
                classLoader.getResourceAsStream("static" + (if (s == "/") "/index.html" else s))
            }) else
            DevelopmentStaticFilesServer({ s ->
                val file = File("/home/ruliov/projects/obliviate/frontend/static" +
                    (if (s == "/") "/index.html" else s))
                if (file.exists()) file.inputStream()
                else null
            })

        val router = HTTPRouter()

        router.addRoute("GET", "/auth/vk", authVkController(staticFilesServer, authProvider))
        router.addRoute("POST", "/log/in/vk", loginVk(authProvider))

        router.addRoute("GET", "/words/", getAllWordsController(database))
        router.addRoute("POST", "/words/", createWordController(database))

        router.addRoute("DELETE",
                Pattern.compile("^/words/(\\d+)$"),
                deleteWordController(database))
        router.addRoute("POST", Pattern.compile("^/words/(\\d+)$"), updateWordController(database))

        router.addRoute("GET", "/words/random", getRandomWordController(database))
        router.addRoute("POST",
                Pattern.compile("^/words/check/(\\d+)$"),
                checkAndGetNextWordController(database))

        router.addRoute("GET", "/admin/resetdb", resetDbController(database))

        val handler = object : IHTTPMiddleware {
            override fun handle(request: Request, next: () -> Unit) {
                val isRouted = router.route(request)
                if (!isRouted) staticFilesServer.handle(request, next)
            }
        }

        val server = JettyServer(handler, PORT)

        server.start()
        server.join()

        createFuture<Any?>(null)
    }.run {
        if (it == null) {
            println("Graceful shutdown")
        } else {
            if (it is Throwable) {
                it.printStackTrace()
            } else {
                System.err.println(it)
                System.exit(1)
            }
        }
    }
}