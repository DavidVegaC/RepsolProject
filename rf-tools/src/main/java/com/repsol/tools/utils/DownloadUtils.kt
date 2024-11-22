package com.repsol.tools.utils

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import java.io.File
import java.io.FileOutputStream


object DownloadUtils {

    fun saveBase64FileToDownload(
        context: Context,
        base64String: String,
        fileName: String,
        mimetype: String,
        isApi29OrHigher: Boolean,
    ): Boolean {
        try {
            val base64Data = base64String.substringAfter("base64,")
            val fileBytes: ByteArray = Base64.decode(base64Data, Base64.DEFAULT)

            if (isApi29OrHigher) {
                val contentResolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, mimetype)
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val uri = contentResolver.insert(
                    MediaStore.Files.getContentUri("external"),
                    contentValues
                )

                uri?.let {
                    contentResolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(fileBytes)
                    }
                }
            } else {
                val documentsDir = Environment.getExternalStorageDirectory()
                val directory = File(documentsDir, Environment.DIRECTORY_DOWNLOADS)
                if (!directory.exists()) {
                    directory.mkdirs()
                }

                val file = File(directory, fileName)

                if (!file.exists()) {
                    file.createNewFile()
                }
                FileOutputStream(file).use { fos ->
                    fos.write(fileBytes)
                    fos.flush()
                }
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}