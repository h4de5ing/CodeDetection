package com.github.h4de5ing.codedetection

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.h4de5ing.zxing.view.ScanCodeView
import com.google.zxing.Result

class ZXingActivity : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
        setContentView(R.layout.activity_zxing)
        findViewById<ScanCodeView>(R.id.scan_code).setOnScanCodeListener(object :
            ScanCodeView.OnScanCodeListener {
            override fun onScanCodeSucceed(result: Result) {
                stringSendChange = "BarcodeFormat：${result.barcodeFormat}\n${result.text}"
                Log.d("gh0st", stringSendChange)
                finish()
            }

            override fun onScanCodeFailed(exception: Exception) {
            }
        })
    }


    fun showToast(tips: String) {
        runOnUiThread { Toast.makeText(this, tips, Toast.LENGTH_SHORT).show() }
    }
}