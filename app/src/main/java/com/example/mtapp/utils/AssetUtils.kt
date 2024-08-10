package com.example.mtapp.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun copyFileFromAssetsToInternalStorage(
    context: Context,
    assetFileName: String,
    outputFileName: String
): String {
    val file = File(context.filesDir, outputFileName)
    context.assets.open(assetFileName).use { inputStream ->
        FileOutputStream(file).use { outputStream ->
            copyFile(inputStream, outputStream)
        }
    }
    return file.absolutePath
}

private fun copyFile(inputStream: InputStream, outputStream: FileOutputStream) {
    val buffer = ByteArray(1024)
    var read: Int
    while (inputStream.read(buffer).also { read = it } != -1) {
        outputStream.write(buffer, 0, read)
    }
}