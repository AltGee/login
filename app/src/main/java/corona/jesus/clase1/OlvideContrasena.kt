package corona.jesus.clase1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OlvideContrasena : AppCompatActivity() {

    private lateinit var layoutCorreo: TextInputLayout
    private lateinit var campoCorreo: TextInputEditText
    private lateinit var btnEnviar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_olvide_contrasena)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        layoutCorreo = findViewById(R.id.layout_correo)
        campoCorreo = findViewById(R.id.campo_correo)
        btnEnviar = findViewById(R.id.btn_enviar)
        btnCancelar = findViewById(R.id.btn_cancelar)

        btnCancelar.setOnClickListener {
            finish()
        }

        btnEnviar.setOnClickListener {
            layoutCorreo.error = null
            val correo = campoCorreo.text.toString().trim()
            if (correo.isEmpty()) {
                layoutCorreo.error = "Por favor ingresa tu correo electrónico"
                Toast.makeText(this, "El correo no puede estar vacío", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Se ha enviado el enlace de recuperación a tu correo", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
