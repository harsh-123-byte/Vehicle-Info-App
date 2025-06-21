package com.cscorner.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


// Here we will upload all data to firebase realtime database.
class UploadActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding

    // we have to create the database reference.
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // ab save button par click karne par kya hoga,jo edittext me data stored hai usse fetch karke firebase par upload karega.
        binding.Savebutton.setOnClickListener {
            val ownername=binding.UploadownerName.text.toString()
            val vehicleBrand=binding.UploadVehivleBrandName.text.toString()
            val vehicleRTO=binding.UploadVehicleRTO.text.toString()
            val vehicleNumber=binding.UploadVehicleNumber.text.toString()

            // initialising firebase.

            databaseReference= FirebaseDatabase.getInstance().getReference("Vehicle Information")

            val vehicleData=vehicleData(ownername,vehicleBrand,vehicleRTO,vehicleNumber)
            // now we will create a child which will be unique and where all our data will be stored.
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                // child me vehicle number isliye liye hain kyoki wo unique hota hai,aur data read karte samay ham vehicle number daal kar us vechicle ka saara information nikaal sakte hain.
                // we will clear the text from edittext once the admin has submitted it.
                binding.UploadownerName.text.clear()
                binding.UploadVehivleBrandName.text.clear()
                binding.UploadVehicleRTO.text.clear()
                binding.UploadVehicleNumber.text.clear()

                // since it is a successlistener so we will show a toast message,that data have been saved in database perfectly.

                Toast.makeText(this,"Data saved successfully",Toast.LENGTH_SHORT).show()

                // now if one admin uploaded the data then we will go to the main activity so that if any another data is to be stores then it get stored.

                val intent= Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                // simply we will show the data is failed to be uploaded.
                Toast.makeText(this,"Failed to save data",Toast.LENGTH_SHORT).show()
            }
        }

    }
}