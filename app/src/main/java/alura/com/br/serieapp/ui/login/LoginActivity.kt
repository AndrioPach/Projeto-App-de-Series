package alura.com.br.serieapp.ui.login

import alura.com.br.serieapp.MainActivity
import alura.com.br.serieapp.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        btn_Entrar.setOnClickListener {
            signIn()
        }

        criar_conta.setOnClickListener {
            startActivity(Intent(this,CadastroActivity::class.java))
            finish()
        }
    }

    private fun signIn() {
        if (login_email.text.toString().isEmpty()) {
            login_email.error = "Digite seu email."
            login_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login_email.text.toString()).matches()) {
            login_email.error = "Por favor, digite um email válido."
            login_email.requestFocus()
            return
        }
        if (login_senha.text.toString().isEmpty()) {
            login_senha.error = "Digite sua senha."
            login_senha.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(login_email.text.toString(),login_senha.text.toString())
            .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(null)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext,
                    "Por favor, verifique seu endereço de email.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}