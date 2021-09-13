package com.example.firebase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //lateinit var binding:ActivityMainBinding

    lateinit var db:DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        auth=Firebase.auth

        //binding= ActivityMainBinding.inflate(layoutInflater)
        val add1 = findViewById<Button>(R.id.add)

        reg.setOnClickListener {
            val i= Intent(this,authentication_activity::class.java)
            startActivity(i)
        }

        add1.setOnClickListener {
            val user=User(firstname.text.toString(),lastname.text.toString(),age.text.toString())
            db=FirebaseDatabase.getInstance().getReference().child("member")

            db.child(firstname.text.toString()).setValue(user).addOnSuccessListener {
                firstname.setText("")
                lastname.setText("")
                age.setText("")
                firstname.requestFocus()
                Toast.makeText(this,"successfully submitted",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                 Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
        dis.setOnClickListener {
            db=FirebaseDatabase.getInstance().getReference("member")
            val s=displayId.text.toString()
            if(s.isNotEmpty()){
                db.child(s).get().addOnSuccessListener {
                    if(it.exists()){
                        val fname=it.child("fname").value
                        val lname=it.child("lname").value
                        var age=it.child("age").value
                        fnames.text=fname.toString()
                        lnames.text= lname.toString()
                        agee.text=age.toString()
                    }
                    else{
                        Toast.makeText(this,"name doesnot exists",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Enter name",Toast.LENGTH_SHORT).show()
            }
        }
    }

}