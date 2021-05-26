package com.codingwithrufat.taxi.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.codingwithrufat.taxi.MainActivity
import com.codingwithrufat.taxi.R
import com.codingwithrufat.taxi.models.ClientModel
import com.codingwithrufat.taxi.models.DriverModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerificationCodeActivity : AppCompatActivity() {

    private lateinit var editVerication: EditText
    private lateinit var verifyButton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var spinnerItem: String
    private val TAG = "VerificationCodeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        initializeWidgets()

        val getCode = intent.getStringExtra("verification_code")
        spinnerItem = intent.getStringExtra("spinnerItem")!!

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

                    firebaseUser = FirebaseAuth.getInstance().currentUser!!

                    modelOfClientAndDriver(spinnerItem)

                    if (firebaseUser != null){
                        startActivity(Intent(this@VerificationCodeActivity, MainActivity::class.java))
                        finish()
                    }

                }
            }.addOnFailureListener {
                Log.w(TAG, "signInWithPhoneAuthCredential: Verification is failure")
            }

    }

    /**
     * it will be completed later
     */
    private fun modelOfClientAndDriver(spinnerItem: String){

        var username = intent.getStringExtra("username")
        var number = intent.getStringExtra("phone")
        var carName = intent.getStringExtra("carName")

        when(spinnerItem){

            "Client" -> {

                var clientModel = ClientModel(firebaseUser.uid, username, number, "")
            }

            "Driver" -> {

                var driverModel = DriverModel(firebaseUser.uid, username, number, carName, "")

            }
        }

    }

}