package com.jams.autenticacao_firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jams.autenticacao_firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // Esta verificação é uma segurança extra. A tela só deve ser aberta se houver usuário.
        if (user == null) {
            goToLoginActivity()
            return
        }

        binding.tvUserEmail.text = "${user.email}"

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        val telaLogin = Intent(this, LoginActivity::class.java)
        startActivity(telaLogin)
        finish()
    }
}