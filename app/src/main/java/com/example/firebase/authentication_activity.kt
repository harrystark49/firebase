package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.BundleCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_authentication.*

class authentication_activity : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        auth=Firebase.auth

        register.setOnClickListener {
            createuser(reg_name.text.toString(),reg_pass.text.toString())
        }
        sign_in.setOnClickListener {
            signin(reg_name.text.toString(),reg_pass.text.toString())
        }

    }

    override fun onStart() {
        super.onStart()
    }
    fun createuser(email:String,pass:String){
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Log.d("Email", "successully user created")
                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Log.w("Email", "failed to register", it)
            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }

    fun signin(email:String,pass:String){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, " Login successful", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, " Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}