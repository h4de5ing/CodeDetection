package com.github.h4de5ing.codedetection

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.zxing.BarcodeFormat

class CodeEncodingActivity : ReturnActivity() {
    private lateinit var codeContent: EditText
    private lateinit var generateBarCode: Button
    private lateinit var imgCode: ImageView
    private var codeName: BarcodeFormat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_encoding)
        val arrayAdapter = ArrayAdapter<BarcodeFormat>(
            this,
            android.R.layout.simple_spinner_item,
            BarcodeFormat.values()
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val sp = findViewById<Spinner>(R.id.sp)
        sp.adapter = arrayAdapter
        sp.selected { codeName = BarcodeFormat.values()[it] }
        codeContent = findViewById<EditText>(R.id.codeContent)
        generateBarCode = findViewById<Button>(R.id.generateBarCode)
        imgCode = findViewById<ImageView>(R.id.img_code)
        generateBarCode.setOnClickListener {
            val input = codeContent.text.toString()
            if (!TextUtils.isEmpty(input)) {
                try {
                    val width = this.resources.displayMetrics.widthPixels - 40
                    val height = width / 5 * 2
                    imgCode.setImageBitmap(makeCode(input, width, height, codeName))
                    codeContent.closeInputMethodAndClearFocus()
                } catch (e: Exception) {
                    Toast.makeText(this, "生成失败,输入不符合【${codeName}】规范！！", Toast.LENGTH_SHORT).show()
                }
            } else {
                codeContent.requestFocus()
                codeContent.error = "please input content"
            }
        }
    }
}