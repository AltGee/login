package corona.jesus.clase1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegistrar: Button
    private lateinit var campoCorreo: EditText
    private lateinit var campoContra: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnContrasena: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnRegistrar = findViewById(R.id.btn_registrar)
        campoCorreo = findViewById(R.id.campo_correo)
        campoContra = findViewById(R.id.campo_contra)
        btnIngresar = findViewById(R.id.btn_ingresar)
        btnContrasena = findViewById(R.id.btn_contrasena)

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        btnContrasena.setOnClickListener {
            val intent = Intent(this, OlvideContrasena::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener {
            val usuario = campoCorreo.text.toString()
            val contra = campoContra.text.toString()
            if (usuario == "admin" && contra == "admin") {
                Toast.makeText(this, "Ingreso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
