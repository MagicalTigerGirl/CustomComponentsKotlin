package com.example.customviewkotlin.customcomponents

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.customviewkotlin.R

class OddEvenEditText: AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inputType = InputType.TYPE_CLASS_NUMBER
        addTextChangedListener(object : TextWatcher {
            // Cambios aplicados y el texto se ha modificado, texto editable

            // Uso: Cuando necesitas ver y editar el nuevo texto añadido.
            override fun afterTextChanged(p0: Editable?) {
                if(!p0.isNullOrEmpty()){
                    if(p0.toString().toDouble()%2 == 0.0){
                        background.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_IN)
                    }else{
                        background.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN)
                    }
                }
            }

            // Entrará aquí antes de que los carácteres sean reemplazados, texto no editable
            // Uso: Cuando necesitas ver el texto antiguo antes de cambiar.
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            // Cambios aplicados y el texto se ha modificado, texto no editable
            // Uso: Cuando necesitas ver los caracteres que han cambiado.
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}