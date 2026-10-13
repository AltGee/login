package corona.jesus.clase1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import java.util.Locale

class Registro : AppCompatActivity() {

    private lateinit var layoutNombre: TextInputLayout
    private lateinit var layoutCorreo: TextInputLayout
    private lateinit var layoutContra: TextInputLayout
    private lateinit var layoutConfirmarContra: TextInputLayout
    private lateinit var layoutFechaNacimiento: TextInputLayout

    private lateinit var campoNombre: TextInputEditText
    private lateinit var campoCorreo: TextInputEditText
    private lateinit var campoContra: TextInputEditText
    private lateinit var campoConfirmarContra: TextInputEditText
    private lateinit var campoFechaNacimiento: TextInputEditText
    private lateinit var btnRegistrar: Button

    private var selectedYear: Int? = null
    private var selectedMonth: Int? = null
    private var selectedDay: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        layoutNombre = findViewById(R.id.layout_nombre)
        layoutCorreo = findViewById(R.id.layout_correo)
        layoutContra = findViewById(R.id.layout_contra)
        layoutConfirmarContra = findViewById(R.id.layout_confirmar_contra)
        layoutFechaNacimiento = findViewById(R.id.layout_fecha_nacimiento)

        campoNombre = findViewById(R.id.campo_nombre)
        campoCorreo = findViewById(R.id.campo_correo)
        campoContra = findViewById(R.id.campo_contra)
        campoConfirmarContra = findViewById(R.id.campo_confirmar_contra)
        campoFechaNacimiento = findViewById(R.id.campo_fecha_nacimiento)
        btnRegistrar = findViewById(R.id.btn_registrar)

        campoFechaNacimiento.setOnClickListener {
            mostrarDatePicker()
        }

        btnRegistrar.setOnClickListener {
            validarFormulario()
        }
    }

    private fun mostrarDatePicker() {
        val calendar = Calendar.getInstance()
        val year = selectedYear ?: calendar.get(Calendar.YEAR)
        val month = selectedMonth ?: calendar.get(Calendar.MONTH)
        val day = selectedDay ?: calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, yearSelected, monthSelected, daySelected ->
                selectedYear = yearSelected
                selectedMonth = monthSelected
                selectedDay = daySelected
                val fechaFormateada = String.format(Locale.getDefault(), "%02d/%02d/%04d", daySelected, monthSelected + 1, yearSelected)
                campoFechaNacimiento.setText(fechaFormateada)
                layoutFechaNacimiento.error = null
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun validarFormulario() {
        layoutNombre.error = null
        layoutCorreo.error = null
        layoutContra.error = null
        layoutConfirmarContra.error = null
        layoutFechaNacimiento.error = null

        val nombre = campoNombre.text.toString().trim()
        val correo = campoCorreo.text.toString().trim()
        val contra = campoContra.text.toString()
        val confirmarContra = campoConfirmarContra.text.toString()
        val fechaNacimiento = campoFechaNacimiento.text.toString().trim()

        var esValido = true

        if (nombre.isEmpty()) {
            layoutNombre.error = "El campo nombre es obligatorio"
            esValido = false
        }
        if (correo.isEmpty()) {
            layoutCorreo.error = "El campo correo es obligatorio"
            esValido = false
        }
        if (contra.isEmpty()) {
            layoutContra.error = "El campo contraseña es obligatorio"
            esValido = false
        }
        if (confirmarContra.isEmpty()) {
            layoutConfirmarContra.error = "El campo verificar contraseña es obligatorio"
            esValido = false
        }
        if (fechaNacimiento.isEmpty() || selectedYear == null) {
            layoutFechaNacimiento.error = "El campo fecha de nacimiento es obligatorio"
            esValido = false
        }

        if (!esValido) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        if (contra != confirmarContra) {
            layoutConfirmarContra.error = "Las contraseñas no coinciden"
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val sYear = selectedYear
        val sMonth = selectedMonth
        val sDay = selectedDay

        if (sYear != null && sMonth != null && sDay != null) {
            val hoy = Calendar.getInstance()
            var edad = hoy.get(Calendar.YEAR) - sYear
            if (hoy.get(Calendar.MONTH) < sMonth ||
                (hoy.get(Calendar.MONTH) == sMonth && hoy.get(Calendar.DAY_OF_MONTH) < sDay)
            ) {
                edad--
            }

            if (edad < 18) {
                layoutFechaNacimiento.error = "Debes ser mayor de 18 años"
                Toast.makeText(this, "Debes ser mayor de 18 años para registrarte", Toast.LENGTH_SHORT).show()
                return
            }
        }

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        finish()
    }
}
