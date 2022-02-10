package com.github.h4de5ing.codedetection

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import kotlin.properties.Delegates


private var changeSendString: Change? = null
var stringSendChange: String by Delegates.observable("<no result>") { _, _, new ->
    changeSendString?.change(new)
}

fun setSendString(ch: ((String) -> Unit)) {
    changeSendString = object : Change {
        override fun change(text: String) {
            ch(text)
        }
    }
}

fun Spinner.selected(block: ((Int) -> Unit)) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            block(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}

fun makeCode(content: String, width: Int, height: Int, format: BarcodeFormat?): Bitmap? {
    val matrix = MultiFormatWriter().encode(content, format, width, height)
    val width = matrix.width
    val height = matrix.height
    val pixels = IntArray(width * height)
    for (y in 0 until height) {
        for (x in 0 until width) {
            if (matrix[x, y]) {
                pixels[y * width + x] = -0x1000000
            }
        }
    }
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
    return bitmap
}

fun View.closeInputMethod() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun EditText.closeInputMethodAndClearFocus() {
    closeInputMethod().apply {
//        isCursorVisible = false
//        isFocusable = false
//        isFocusableInTouchMode = false
        clearFocus()
    }
}