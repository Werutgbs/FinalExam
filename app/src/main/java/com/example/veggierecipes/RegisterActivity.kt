package com.example.veggierecipes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        findViewById<Button>(R.id.register_button_register).setOnClickListener {

            performRegister()
        }

        findViewById<TextView>(R.id.already_have_account).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.selectphoto_imageview_register).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            startActivityForResult(intent, 1)

        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Glide.with(this).load(selectedPhotoUri)
                .into(findViewById(R.id.selectphoto_imageview_register))

        }
    }

    private fun performRegister() {

        val email = findViewById<EditText>(R.id.email_edittext_register).text.toString()
        val password = findViewById<EditText>(R.id.password_edittext_register).text.toString()
        Log.d("asd", "Email is : $email")
        Log.d("asd", "Password is : $password")

        if (email.isEmpty()) {

            Toast.makeText(
                this,
                "Please fill email - password fields (min 6 symbols)",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(
                this,
                "Please Enter Password (min 6 char)",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (selectedPhotoUri == null) {
            Toast.makeText(
                this,
                "Please select image",
                Toast.LENGTH_SHORT
            ).show()
            return
        }



        register_button_register.isClickable = false
        already_have_account.isClickable = false



        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("asd", "Successfully created user with uid  : ${it.result?.user?.uid} ")
                uploadImageToFirebaseStorage()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create user : ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }

    private fun uploadImageToFirebaseStorage() {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener { it ->
                Log.d("asd", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("asd", "File Location: $it")

                    saveUserToFirebaseDatabase(it.toString())

                }
            }
            .addOnFailureListener {
                Log.d("asd", "Failed to upload image to storage: ${it.message}")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid
        val database = Firebase.database
        val ref = database.getReference("users/$uid")
        val user = User(
            uid!!,
            findViewById<EditText>(R.id.username_edittext_register).text.toString(),
            profileImageUrl
        )



        ref.setValue(user)
            .addOnSuccessListener {
                val intent = Intent(this, LatestRecipesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("asd", "Failed to set value to database: ${it.message}")
                Toast.makeText(this, "Failed to set value to database", Toast.LENGTH_SHORT).show()
            }
    }
}
