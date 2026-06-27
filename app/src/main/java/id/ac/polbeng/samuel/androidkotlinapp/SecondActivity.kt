package id.ac.polbeng.samuel.androidkotlinapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.polbeng.samuel.androidkotlinapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 🔹 AMBIL DATA NAMA

        val nama = intent.getStringExtra("NAMA")
        binding.tvNama.text = "Halo, $nama"


        // 🔹 AMBIL DATA BMI

        val bundle = intent.getBundleExtra("BMI_DATA")
        val weight = bundle?.getDouble("weight")
        val height = bundle?.getDouble("height")

        binding.txtIntentData.text = "Weight: $weight | Height: $height"


        // 🔹 HITUNG BMI & KIRIM BALIK

        binding.btnCalculate.setOnClickListener {

            var bmi = 0.0

            if (weight != null && height != null) {
                bmi = 703 * (weight / (height * height))
            }

            val resultIntent = Intent()
            resultIntent.putExtra("BMI_RESULT", bmi)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }


        // 🔹 TOMBOL KELUAR

        binding.btnExitActivity.setOnClickListener {
            finish()
        }
    }
}