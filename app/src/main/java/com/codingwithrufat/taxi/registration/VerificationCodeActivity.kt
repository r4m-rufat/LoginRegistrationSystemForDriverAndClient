package com.codingwithrufat.taxi.registration

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingwithrufat.taxi.MainActivity
import com.codingwithrufat.taxi.R
import com.codingwithrufat.taxi.models.ClientModel
import com.codingwithrufat.taxi.models.DriverModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class VerificationCodeActivity : AppCompatActivity() {

    private lateinit var editVerication: EditText
    private lateinit var verifyButton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var spinnerItem: String
    private lateinit var firestore: FirebaseFirestore
    private lateinit var progressDialog: ProgressDialog
    private val TAG = "VerificationCodeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        initializeWidgets()

        val getCode = intent.getStringExtra("verification_code")
        spinnerItem = intent.getStringExtra("spinnerItem")!!

        verifyButton.setOnClickListener {
            if (TextUtils.isEmpty(editVerication.text.toString())){
                editVerication.error = "Please, enter verification code!"
            }else{
                progressDialog = ProgressDialog(this@VerificationCodeActivity)
                progressDialog.setMessage("Waiting...")
                progressDialog.setCancelable(false)
                progressDialog.show()
                verifyPhoneNumberWithCode(getCode!!, editVerication.text.toString().trim())
            }
        }

    }

    private fun initializeWidgets() {

        editVerication = findViewById(R.id.editTextVerifyCode)
        verifyButton = findViewById(R.id.buttonVerify)

    }

    private fun verifyPhoneNumberWithCode(verificationID: String, code: String) {

        var credential = PhoneAuthProvider.getCredential(verificationID, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    firebaseUser = FirebaseAuth.getInstance().currentUser!!

                    modelOfClientAndDriver(spinnerItem)

                    if (firebaseUser != null) {
                        startActivity(
                            Intent(
                                this@VerificationCodeActivity,
                                MainActivity::class.java
                            )
                        )
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
    private fun modelOfClientAndDriver(spinnerItem: String) {

        var username = intent.getStringExtra("username")
        var number = intent.getStringExtra("phone")
        var carName = intent.getStringExtra("carName")
        firestore = FirebaseFirestore.getInstance()

        when (spinnerItem) {

            "Client" -> {

                var clientModel = ClientModel(
                    firebaseUser.uid,
                    username,
                    number,
                    ""
                )

                firestore.document("Users")
                    .collection("Clients")
                    .document(firebaseUser.uid)
                    .set(clientModel)
                    .addOnSuccessListener {

                        progressDialog.dismiss()
                        Toast.makeText(
                            this,
                            "Successfully verified your account as client",
                            Toast.LENGTH_SHORT
                        ).show()

                    }.addOnFailureListener {

                        progressDialog.dismiss()
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "modelOfClientAndDriver: Client failure")

                    }
            }

            "Driver" -> {

                var driverModel = DriverModel(
                    firebaseUser.uid,
                    username,
                    number,
                    carName,
                    ""
                )

                firestore.document("Users")
                    .collection("Drivers")
                    .document(firebaseUser.uid)
                    .set(driverModel)
                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Successfully verified your account as driver",
                            Toast.LENGTH_SHORT
                        ).show()
                        progressDialog.dismiss()

                    }.addOnFailureListener {

                        progressDialog.dismiss()
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "modelOfClientAndDriver: Driver failure")

                    }

            }
        }

    }

}