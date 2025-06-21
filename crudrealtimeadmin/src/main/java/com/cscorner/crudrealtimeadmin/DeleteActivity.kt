package com.cscorner.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Here we will fetch the vehicle number which has to be deleted and pass it to the deleteData() function.
        binding.deleteButton.setOnClickListener {
            val vehicleNumber = binding.deleteVehiclenumber.text.toString()
            if (vehicleNumber.isNotEmpty()) {
                deleteData(vehicleNumber)
            }
            else
            {
                Toast.makeText(this, "Please enter the vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Now we will create a function which will perform the deletion after it is called after pressing delete button.
    fun deleteData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

        // we use removeValue() method to remove the data from the database.
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deleteVehiclenumber.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}