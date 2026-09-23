package com.example.longarms

import android.os.Bundle
import android.widget.ToggleButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleButton = findViewById<ToggleButton>(R.id.toggleLongArms)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                runShellCommand("setprop debug.oculus.headlock 3")
                Toast.makeText(this, "Long Arms ON", Toast.LENGTH_SHORT).show()
            } else {
                runShellCommand("setprop debug.oculus.headlock 0")
                Toast.makeText(this, "Long Arms OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun runShellCommand(command: String) {
        try {
            val process = Runtime.getRuntime().exec("sh")
            val os = DataOutputStream(process.outputStream)
            os.writeBytes("$command\n")
            os.writeBytes("exit\n")
            os.flush()
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

