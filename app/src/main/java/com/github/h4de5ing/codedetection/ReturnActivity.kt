package com.github.h4de5ing.codedetection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ReturnActivity : AppCompatActivity() {
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
    }
}