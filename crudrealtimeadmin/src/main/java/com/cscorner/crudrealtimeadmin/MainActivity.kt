package com.cscorner.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.crudrealtimeadmin.databinding.ActivityMainBinding


// ye admin page hai,we will create an app within a given app,matlab do kaam ke liye app banega.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // we have to create that what will happen on presssing the upload button.
        binding.mainUpload.setOnClickListener {
            val intent= Intent(this@MainActivity,UploadActivity::class.java)
            startActivity(intent)//then only it will go to another page.
            // ek baar khulne ke baad again na khule isliye finish karna zaroori hai.
            finish()
        }


        // now we will create that what will happen after pressing update button.

        // main activity wale update button par click karne par ham update activity wale page pe chale jayenge.

        binding.mainUpdate.setOnClickListener {
            val intent=Intent(this@MainActivity,UpdateActivity::class.java)
            startActivity(intent)
            finish()

        }


        // Now we will work on the deletion of the Data from the base by pressing Delete Button.

        binding.mainDelete.setOnClickListener {
            val intent=Intent(this@MainActivity,DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}