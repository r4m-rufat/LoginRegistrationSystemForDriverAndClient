package com.codingwithrufat.taxi.registration

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codingwithrufat.taxi.MainActivity
import com.codingwithrufat.taxi.R
import com.codingwithrufat.taxi.common.Common

class LoginActivity : AppCompatActivity() {

    private lateinit var txtCreate: TextView
    private lateinit var editTextPhone: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var relativeSignIn: RelativeLayout
    private lateinit var txtForgotPassword: TextView
    private lateinit var iconEye: ImageView
    private lateinit var signInText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getWidgets()
        clickedCreateAndForgotPassword()
        commonClassMethods()

    }

    private fun getWidgets() {

        txtCreate = findViewById(R.id.txtCreate)
        editTextPhone = findViewById(R.id.editTextPhoneNumberSign)
        editTextPassword = findViewById(R.id.editTextPasswordSign)
        relativeSignIn = findViewById(R.id.relativeSignIn)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        iconEye = findViewById(R.id.ic_eyeForSignInPage)
        signInText = findViewById(R.id.txtSign)

    }

    private fun clickedCreateAndForgotPassword() {

        signInText.setOnClickListener {
            setupEditTextCharacters()
        }

        txtCreate.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }

        txtForgotPassword.setOnClickListener {
            // TODO(Not yet implemented)
        }

    }

    // -- common class is helper class
    private fun commonClassMethods() {

        // password is visible or not statement
        Common().clickedIcEye(iconEye, editTextPassword, this)

    }

    private fun setupEditTextCharacters() {

        val textOfPassword = editTextPassword.text.toString().trim()
        val textOfPhone = editTextPhone.text.toString().trim()

        // -- if all statements is true then passes MainActivity
        if (!TextUtils.isEmpty(textOfPassword)
                && !TextUtils.isEmpty(textOfPhone)
                && (textOfPassword.length >= 6)) {

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()

        } else { // -- but if only one statement is not true then set error message to EditText

            if (textOfPhone.isEmpty()) {

                editTextPhone.error = "Write your number correctly"

            }

            if (textOfPassword.length < 6) {

                editTextPassword.error = "At least 6 characters!"

            }

        }
    }


}