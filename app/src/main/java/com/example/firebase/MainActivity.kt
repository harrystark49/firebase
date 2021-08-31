package com.example.firebase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //lateinit var binding:ActivityMainBinding
    lateinit var db:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding= ActivityMainBinding.inflate(layoutInflater)

        add.setOnClickListener {
            var user=User(firstname.text.toString(),lastname.text.toString(),age.text.toString())

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
            var s=displayId.text.toString()
            if(s.isNotEmpty()){
                db.child(s).get().addOnSuccessListener {
                    if(it.exists()){
                        var fname=it.child("fname").value
                        var lname=it.child("lname").value
                        var age=it.child("age").value
                        fnames.text=fname.toString()
                        lnames.text= lname.toString()
                        agee.text=age.toString()
                    }
                    else{
                        Toast.makeText(this,"name doesnot exists",Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Enter name",Toast.LENGTH_SHORT).show()
            }

        }
    }
}