package com.cscorner.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    // initialise the database
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        // The datas which we want to be updated are stored in each edit texts,now after pressing the update button we will send them to updatedata function and the updation process will get performed.
        binding.Updatebutton.setOnClickListener {

            // Button ke click hone ke baad saara input lena hai edit text me.

            var referenceVehicleNumber=binding.referenceVehicleNumber.text.toString()
            var ownerName=binding.UpdateownerName.text.toString()
            var vehicleBrand=binding.UpdateVehivleBrandName.text.toString()
            var vehicleRTO=binding.UpdateVehicleRTO.text.toString()
            updateData(referenceVehicleNumber,ownerName,vehicleBrand,vehicleRTO)
        }


    }

    // we will create a function for updating the data,in which we will take the parameters as all the things which we have to update.
    // **** Always remember for updating the data from the database,we must understand it was stored in the form of key-value pair.--->so we will use mapof<> data structures.

    private fun updateData(vehicleNumber:String,ownerName:String,vehicleBrand:String,vehicleRTO:String) {
        // The first work is that we have to choose the database reference inwhich we have to make updations.

        databaseReference= FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val updateData=mapOf<String,String>(
            "ownername" to ownerName,  // purane wale ki gajah ab naya wala dalenge.
            "vehicleBrand" to vehicleBrand,
            "vehicleNumber" to vehicleNumber,
            "vehicleRTO" to vehicleRTO)
        // but we have to take reference of vehicle number to make updations in other informations.
        databaseReference.child(vehicleNumber).updateChildren(updateData).addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            binding.UpdateownerName.text.clear()
            binding.UpdateVehivleBrandName.text.clear()
            binding.UpdateVehicleRTO.text.clear()

            // Saare edittext next input ke liye khali ho gye aur,ek toast message denge ki successfully data update ho gya.

            Toast.makeText(this,"Successfully Updated the Data",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }
    }
}