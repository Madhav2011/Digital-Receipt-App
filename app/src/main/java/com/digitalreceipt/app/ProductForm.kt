package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.digitalreceipt.app.dataclass.NewProductDataClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_product_form.*
import java.util.*

class ProductForm : AppCompatActivity() {
    private lateinit var imageUri: Uri
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private var fileName: String = ""
    companion object {
        var last = -1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)
        getLastId()
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        imageView2.setOnClickListener {
            choosePicture()
        }

        buttonAdd.setOnClickListener {
            if(last!=-1) {
                val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val database = FirebaseDatabase.getInstance()
                val myRef =
                    database.getReference("BillData").child(preferences.getString("user", null)!!)
                myRef.child((last+1).toString()).setValue(
                    NewProductDataClass(
                        last+1,
                        fileName,
                        entprodname.editText!!.text.toString(),
                        entdopname.editText!!.text.toString(),
                        entbillnum.editText!!.text.toString(),
                        entwarcardnum.editText!!.text.toString(),
                        entexprdate.editText!!.text.toString(),
                        entnoshop.editText!!.text.toString(),
                        entnameofshop.editText!!.text.toString(),
                        entupload.editText!!.text.toString(),
                        enttedst.editText!!.text.toString()
                    )
                ).addOnCompleteListener {
                    startActivity(Intent(this, homepage1::class.java))
                }
            }
        }
    }

    private fun getLastId() {
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("BillData").child(preferences.getString("user", null)!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                last = snapshot.childrenCount.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun choosePicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            imageUri = data!!.data!!
            imageView2.setImageURI(imageUri)
            uploadPicture()
        }
    }

    private fun uploadPicture() {
        val randomKey = UUID.randomUUID().toString()
        val profileRef = storageReference.child("bill/${randomKey}")
        profileRef.putFile(imageUri)
            .addOnSuccessListener {
                Toast.makeText(this, "uploading...", Toast.LENGTH_SHORT).show()
                fileName =
                    "https://firebasestorage.googleapis.com/v0/b/digital-receipt-5c505.appspot.com/o/bill%2F${it.storage.name}?alt=media&token=27b5e53f-93dd-4acc-9f87-4c77d85653bc"
            }
            .addOnFailureListener {
                Toast.makeText(this, "failed to upload", Toast.LENGTH_SHORT).show()
            }
    }

}