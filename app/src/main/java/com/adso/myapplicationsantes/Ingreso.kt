package com.adso.myapplicationsantes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.adso.myapplicationsantes.databinding.ActivityIngresoBinding
import com.adso.myapplicationsantes.databinding.IngresoBinding
import com.adso.myapplicationsantes.models.Loginrequest
import com.adso.myapplicationsantes.models.Loginresponse
import com.adso.myapplicationsantes.network.Retrofitclient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ingreso : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }
    /*private lateinit var binding: ActivityIngresoBinding*/

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var emailEditText: EditText


    /*@SuppressLint("WrongViewCast")*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso)

        // Configurar opciones de inicio de sesión con Google
        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)*/

        // Obtener referencia al botón de inicio de sesión con Google
        val googleSignInButton: Button = findViewById(R.id.googleSignInButton)
        googleSignInButton.setOnClickListener(View.OnClickListener { signInWithGoogle() })

        // Obtener referencia al botón de acceder
        val accederButton: Button = findViewById(R.id.loginButton)
        accederButton.setOnClickListener {
            val mensajeBienvenida = "¡Bienvenido a Ayuemplen! Donde las oportunidades te están esperando."
            Toast.makeText(this, mensajeBienvenida, Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        // Obtener referencia al botón de registrarse
        val registrateButton: Button = findViewById(R.id.registerButton)
        registrateButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Obtener referencia al botón "Olvidaste tu contraseña?"
        val OlvideContra: Button = findViewById(R.id.forgotPasswordText)
        OlvideContra.setOnClickListener {
            val intent = Intent(this@Ingreso, PasswordRecovery::class.java)
            startActivity(intent)

        }

        accederButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.correo).text.toString()
            val password = findViewById<EditText>(R.id.contraseña).text.toString()
            Retrofitclient.apiservice.login(Loginrequest(email, password))
                .enqueue(object : Callback<Loginresponse> {
                    override fun onResponse(
                        call: Call<Loginresponse>,
                        response: Response<Loginresponse>
                    ) {
                        if (response.isSuccessful) {
                            // Guarda el token en SharedPreferences
                            val token = response.body()?.token
                            val sharedPreferences =

                                getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("token", token).apply()

                            // Navega a la actividad Home
                            val intent = Intent(this@Ingreso, Home::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Maneja el error
                            Toast.makeText(this@Ingreso, "Login failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<Loginresponse>, t: Throwable) {
                        Toast.makeText(this@Ingreso, "Network error", Toast.LENGTH_SHORT).show()
                    }
                })

        }
    }


    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    private fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Resultado devuelto por la actividad de inicio de sesión de Google
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Inicio de sesión exitoso con Google
                val account = task.getResult(ApiException::class.java)
                showSuccessfulRegistrationMessage()
                navigateToHome()
            } catch (e: ApiException) {
                // Error en el inicio de sesión con Google
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSuccessfulRegistrationMessage() {
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }
}