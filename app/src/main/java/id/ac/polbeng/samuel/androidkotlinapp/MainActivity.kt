package id.ac.polbeng.samuel.androidkotlinapp

import id.ac.polbeng.samuel.androidkotlinapp.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import android.content.Intent

const val SECOND_ACTIVITY = 1000

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCounter.text = "1"

        // CLICK (Toast)
        binding.btnDisplayMessage.setOnClickListener {
            Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show()
        }

        // LONG CLICK (Snackbar)
        binding.btnDisplayMessage.setOnLongClickListener {
            Snackbar.make(binding.root, "Long Click", Snackbar.LENGTH_LONG).show()
            true
        }

        // INTENT (Kirim Nama)
        binding.btnToSecondActivity.setOnClickListener {
            val nama = binding.editTextName.text.toString()

            if (nama.isEmpty()) {
                Toast.makeText(this, "Masukkan nama dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAMA", nama)
            startActivity(intent)
        }

        // BMI
        binding.btnSendBMI.setOnClickListener {

            val nama = binding.editTextName.text.toString() // <-- Tambahkan ini

            val weight = binding.inputWeight.text.toString().toDoubleOrNull()
            val height = binding.inputHeight.text.toString().toDoubleOrNull()

            if (nama.isEmpty()) {
                Toast.makeText(this, "Masukkan nama dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (weight == null || height == null) {
                Toast.makeText(this, "Isi weight dan height dengan benar!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("NAMA", nama)

            val bundle = Bundle()
            bundle.putDouble("weight", weight)
            bundle.putDouble("height", height)

            intent.putExtra("BMI_DATA", bundle)

            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }

    // TERIMA HASIL BMI
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY && resultCode == RESULT_OK) {
            val bmi = data?.getDoubleExtra("BMI_RESULT", 0.0)
            binding.txtBMI.text = "BMI: %.2f".format(bmi)
        }
    }

    // COUNTER FUNCTIONS
    fun addNumber(v: View) {
        val currVal = binding.tvCounter.text.toString().toInt()
        val nextVal = currVal + 1
        binding.tvCounter.text = nextVal.toString()
    }

    fun subtractNumber(v: View) {
        val currVal = binding.tvCounter.text.toString().toInt()
        val nextVal = currVal - 1
        binding.tvCounter.text = nextVal.toString()
    }

    fun resetNumber(v: View) {
        binding.tvCounter.text = "0"
    }
}