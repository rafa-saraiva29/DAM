package dam_a47484.systeminfoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val multilineText: EditText = findViewById(R.id.editTextTextMultiLine)

        val systemStr = "Manufacturer: ${Build.MANUFACTURER}\n" +
                "Model: ${Build.MODEL}\n" +
                "Brand: ${Build.BRAND}\n" +
                "Type: ${Build.TYPE}\n" +
                "User: ${Build.USER}\n" +
                "Incremental: ${Build.VERSION.INCREMENTAL}\n" +
                "SDK: ${Build.VERSION.SDK_INT}\n" +
                "Version Code: ${Build.VERSION.RELEASE}\n" +
                "Display: ${Build.DISPLAY}"

        multilineText.setText(systemStr)

    }
}