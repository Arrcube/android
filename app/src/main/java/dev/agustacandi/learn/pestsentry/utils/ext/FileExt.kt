package dev.agustacandi.learn.pestsentry.utils.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import dev.agustacandi.learn.pestsentry.utils.ConstVal
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun File.reduceFileImage(): File {
    val file = this
    val bitmap = BitmapFactory.decodeFile(file.path)
    var compressQuality = 100
    var streamLength: Int
    do {
        val bmpStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
        val bmpPicByteArray = bmpStream.toByteArray()
        streamLength = bmpPicByteArray.size
        compressQuality -= 5
    } while (streamLength > ConstVal.MAXIMAL_SIZE)
    bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
    return file
}