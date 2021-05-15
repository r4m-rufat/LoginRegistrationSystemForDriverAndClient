package com.codingwithrufat.taxi.common

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class Common {

    /**
     * when clicked eye image changes its input type {TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_WEB_PASSWORD}
     */
    fun clickedIcEye(ic_eye: ImageView, editText: EditText, context: Context){

        editText.transformationMethod = PasswordTransformationMethod.getInstance()

        var boolean = true
        ic_eye.setOnClickListener {

            boolean = if (boolean){

                editText.transformationMethod == HideReturnsTransformationMethod.getInstance()
                Toast.makeText(context, "HideReturn", Toast.LENGTH_SHORT).show()
                false

            }else{

                editText.transformationMethod == PasswordTransformationMethod.getInstance()
                true
            }

        }

    }

}