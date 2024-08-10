import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun downloadFileFromDropbox(context: Context, fileUrl: String, outputFilename: String): String? {
    val client = OkHttpClient()
    val request = Request.Builder().url(fileUrl).build()

    return try {
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected response $response")

        val inputStream = response.body?.byteStream()
        val file = File(context.filesDir, outputFilename)
        val outputStream = FileOutputStream(file)

        inputStream?.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
            }
        }

        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}