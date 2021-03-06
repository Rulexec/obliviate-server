package ruliov.jetty.static

import org.eclipse.jetty.server.Request
import ruliov.jetty.IHTTPMiddleware
import ruliov.toHexString
import java.io.InputStream
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap
import java.util.zip.GZIPOutputStream

class StaticFilesServer(private var resolver: (String) -> InputStream?)
        : IHTTPMiddleware, IStaticFilesServer {
    private data class CachedFile(val bytes: ByteArray, val etag: String)
    // TODO: better to inject cache in resolver, but I don't want implement InputStream
    private val cache = ConcurrentHashMap<String, CachedFile>()

    override fun serveFile(fileName: String, request: Request): Boolean {
        var cached = cache[fileName]
        val bytes: ByteArray

        if (cached == null) {
            val inputStream = resolver(fileName) ?: return false

            bytes = inputStream.toByteArray()
            val md = MessageDigest.getInstance("MD5")

            md.update(bytes)

            val digest = md.digest()
            val etag = "1-" + digest.toHexString()

            cached = CachedFile(bytes, etag)
            cache[request.requestURI] = cached
        } else {
            bytes = cached.bytes
        }

        if (request.getHeader("If-None-Match")?.equals(cached.etag) ?: false) {
            request.response.setStatusWithReason(304, "Not Modified")
            return true
        }

        addMimeTypeHeaderByFileName(request.requestURI, request.response)
        request.response.setHeader("Cache-Control", "public, must-revalidate")
        request.response.setHeader("ETag", cached.etag)

        val acceptEncoding = request.getHeader("Accept-Encoding")
        if (acceptEncoding?.contains("gzip", true) == true) {
            // TODO: we can cache gzipped content
            request.response.setHeader("Content-Encoding", "gzip")
            val gzipStream = GZIPOutputStream(request.response.outputStream)
            gzipStream.write(bytes)
            gzipStream.finish()
        } else {
            request.response.outputStream.write(bytes)
        }

        request.response.closeOutput()

        return true
    }
}