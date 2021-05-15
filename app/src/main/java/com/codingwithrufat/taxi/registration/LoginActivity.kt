package com.codingwithrufat.taxi.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.codingwithrufat.taxi.R
import com.codingwithrufat.taxi.common.Common
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private lateinit var txtCreate: TextView
    private lateinit var editTextPhone: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var relativeSignIn: RelativeLayout
    private lateinit var txtForgotPassword: TextView
    private lateinit var iconEye: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getWidgets()
        clickedCreateAndForgotPassword()
        commonClassMethods()

    }

    private fun getWidgets(){

        txtCreate = findViewById(R.id.txtCreate)
        editTextPhone = findViewById(R.id.editTextPhoneNumberSign)
        editTextPassword = findViewById(R.id.editTextPasswordSign)
        relativeSignIn = findViewById(R.id.relativeSignIn)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        iconEye = findViewById(R.id.ic_eyeForSignInPage)

    }

    private fun clickedCreateAndForgotPassword(){

        txtCreate.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }

        txtForgotPassword.setOnClickListener {
            // TODO(Not yet implemented)
        }

    }

    // -- common class is helper class
    private fun commonClassMethods(){

        // password is visible or not statement
        Common().clickedIcEye(iconEye, editTextPassword, this)

    }

}