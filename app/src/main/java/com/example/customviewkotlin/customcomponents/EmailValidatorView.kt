package com.example.customviewkotlin.customcomponents

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.customviewkotlin.R
import java.util.regex.Pattern


class EmailValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs),
    TextWatcher {


    var successColor: Int
    var errorColor: Int
    val etMail: EditText
    val tvErrorCode: TextView

    init {
        inflate(context, R.layout.email_validator, this)


        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MailValidator, 0, 0)

        etMail = findViewById<EditText>(R.id.etMail)
        tvErrorCode = findViewById<TextView>(R.id.tvErrorCode)

        tvErrorCode.text = attributes.getString(R.styleable.MailValidator_textError)
        errorColor = attributes.getColor(R.styleable.MailValidator_underlineErrorColor, ContextCompat.getColor(context, R.color.colorAccent))
        successColor = attributes.getColor(R.styleable.MailValidator_underlineSuccessColor, ContextCompat.getColor(context, R.color.colorAccent))
        attributes.recycle()
        etMail.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


        val pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
        val matcher = pattern.matcher(s.toString())
        val valid = matcher.matches()
        if (valid) {
            tvErrorCode.visibility = View.INVISIBLE
            etMail.background.setColorFilter(successColor, PorterDuff.Mode.SRC_IN)
        } else {
            tvErrorCode.visibility = View.VISIBLE
            etMail.background.setColorFilter(errorColor, PorterDuff.Mode.SRC_IN)
        }
    }
}