package com.github.h4de5ing.codedetection

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
        findViewById<Button>(R.id.start).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ZXingActivity::class.java
                )
            )
        }
        setSendString { text.text = it }
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                Log.d("gh0st", "授权结果:${granted}")
            }
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.codegen) {
            startActivity(Intent(this, CodeEncodingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}