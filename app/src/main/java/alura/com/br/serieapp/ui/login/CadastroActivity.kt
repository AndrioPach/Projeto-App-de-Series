package alura.com.br.serieapp.ui.login

import alura.com.br.serieapp.R
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        auth = FirebaseAuth.getInstance()

        entrar_cadastro.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_Cadastrar.setOnClickListener {
            criaConta()
        }
    }

    private fun criaConta() {

        auth.createUserWithEmailAndPassword(
            cadastro_email.text.toString(),
            cadastro_senha.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail: success")
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener{task->
                        if (task.isSuccessful){
                            startActivity(Intent(this,LoginActivity::class.java))
                            finish()
                        }
                    }

                } else {
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Erro ao efetuar o login. Tente novamente.", Toast.LENGTH_SHORT).show()
//                    updateUI(null)

                }
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
    class User(val uid: String, val email: String) {
        constructor() : this("", "")
    }
}