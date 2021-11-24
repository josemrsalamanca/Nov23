package com.example.a23nov_evi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.a23nov_evi.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ultimo paso
        binding.login.setOnClickListener {
            validate() }
    }

    // validacion mail
    private fun validateEmail():Boolean {
        val email = binding.username.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.username.error = "Field can not be empty"
            false
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.username.error = "Please enter a valid email address"
            false
        }else{
            binding.username.error = null
            true
        }
    }

    // validacion password
    private fun validatePassword():Boolean{
        val clave = binding.password.editText?.text.toString()

        //expresiones regulares
        val claveER= Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +              // al menos un num
                    "(?=.*[a-z])" +              // al menos una palabra minus
                    "(?=.*[A-Z])" +              // al menos una palabra mayus
                    "(?=.*[@#$%^&+=])" +         // un caracter especial
                    "(?=\\S+$)" +                // sin espacios
                    ".{5,}" +                    // al menos 5 caracteres
                    "$"

        )
        return if (clave.isEmpty()){
            binding.password.error =  "Field can not be empty"
            false
        }else if(!claveER.matcher(clave).matches()){
            binding.password.error = "Password is too week"
            false
        }else{
            binding.password.error = null
            true
        }
    }

    //vamos a crear una f(x) donde tengamos los resultados booleanos de validar email y password

    private fun validate(){
        val result = arrayOf(validateEmail(),validatePassword())
            if (false in result){
                return
            }
            Toast.makeText(this,"Sucess",Toast.LENGTH_LONG).show()
    }
}