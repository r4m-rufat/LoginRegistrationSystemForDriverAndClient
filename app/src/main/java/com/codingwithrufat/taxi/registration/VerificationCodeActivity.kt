package com.codingwithrufat.taxi.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.codingwithrufat.taxi.MainActivity
import com.codingwithrufat.taxi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerificationCodeActivity : AppCompatActivity() {

    private lateinit var editVerication: EditText
    private lateinit var verifyButton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        initializeWidgets()

        val getCode = intent.getStringExtra("verification_code")

        verifyButton.setOnClickListener {
            verifyPhoneNumberWithCode(getCode!!, editVerication.text.toString().trim())
        }

    }

    private fun initializeWidgets(){

        editVerication = findViewById(R.id.editTextVerifyCode)
        verifyButton = findViewById(R.id.buttonVerify)

    }

    private fun verifyPhoneNumberWithCode(verificationID: String, code: String){

        var credential = PhoneAuthProvider.getCredential(verificationID, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){

                    firebaseUser = it.result!!.user!!

                    if (firebaseUser != null){
                        startActivity(Intent(this@VerificationCodeActivity, MainActivity::class.java))
                        finish()
                    }

                }
            }

    }

}