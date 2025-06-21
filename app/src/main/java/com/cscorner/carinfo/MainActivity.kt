package com.cscorner.carinfo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.carinfo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // on pressing the search button what will happen--->we will show the retrieved data by calling the function readData().

        binding.searchButton.setOnClickListener{
            val vehicleNumber:String=binding.searchVehicleNumber.text.toString()
            if(vehicleNumber.isNotEmpty())
            {
                readData(vehicleNumber)
            }
            else
            {
                Toast.makeText(this,"Please Enter Vehicle Number",Toast.LENGTH_SHORT).show()
            }
        }
    // we will create a function read data in which we will retrive all the data which is stored in the database and then will display it when the search button is clicked.
    }



    fun readData(vehicleNumber:String)
    {
        databaseReference= FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener{
            if(it.exists()) // it is here the vehicle number which is taken as input.
            {
                val ownerName=it.child("ownername").value  // here value act as a get value method.
                val vehicleBrand=it.child("vehicleBrand").value
                val vehicleRTO=it.child("vehicleRTO").value

                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()

                binding.searchVehicleNumber.text.clear()

                // as we retrieved the data from the respective branches of the database convert them to string to show on their respective textviews.
                binding.readOwnerName.text=ownerName.toString()
                binding.readVehicleBrand.text=vehicleBrand.toString()
                binding.readVehicleRTO.text=vehicleRTO.toString()

            }
            else
            {
                Toast.makeText(this,"Vehicle Number Not Found",Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()

        }
    }
}