package com.codingwithrufat.taxi.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.codingwithrufat.taxi.R
import org.w3c.dom.Text
import kotlin.math.log
import kotlin.math.sign

class WelcomeScreen : AppCompatActivity() {

    private lateinit var signup: TextView
    private lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        signup = findViewById(R.id.txtSignUp)
        login = findViewById(R.id.txtLogin)

        signup.setOnClickListener {
            startActivity(Intent(this@WelcomeScreen, RegisterActivity::class.java))
            finish()
        }

        login.setOnClickListener {
            startActivity(Intent(this@WelcomeScreen, LoginActivity::class.java))
            finish()
        }

    }
}