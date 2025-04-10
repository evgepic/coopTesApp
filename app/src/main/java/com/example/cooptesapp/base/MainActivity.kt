package com.example.cooptesapp.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cooptesapp.R
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BaseUiActions {

    private val viewmodel: MainViewModel by viewModels()
    var binding: ActivityMainBinding? = null
    private var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        progressDialog = createProgressDialog()
        isFirstLaunch()
    }

    private fun isFirstLaunch() {
        val key = "IFL"
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        if (sharedPref.getBoolean(key, true)) {
            with(sharedPref.edit()) {
                putBoolean(key, false)
                apply()
            }
            val list = DBHelper.createBDCollection()
            (application as DataBaseInstance).let {
                val dbRep = DataBaseRepositoryInstance(
                    it.getDataBaseInstance().barcodeDao(),
                    it.getDataBaseInstance().packDao(),
                    it.getDataBaseInstance().packPriceDao(),
                    it.getDataBaseInstance().unitDao()
                )
                viewmodel.insert(dbRep, list)
            }
        }
    }

    override fun showToast(stringResId: Int) {
        val toast = Toast.makeText(
            this,
            stringResId,
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    override fun showError(string: String) {
        endLoading()
        createErrorDialog(string)
    }

    override fun showError(throwable: Throwable) {
        endLoading()
        val msg = when (throwable) {
            is InputValidation.EmptyField -> "Пустое поле ввода"
            is InputValidation.EmptyPasswordField -> "Пустое поле пароль"
            is InputValidation.WeakPass ->
                "Пароль должен быть больше 6 символов"

            is InputValidation.WrongEmail -> "Некорректный email"
            is InputValidation.UserNotExist -> "Пользователя не существует"
            is InputValidation.PassNotCompare -> "Пароли не совпадают"
            else -> throwable.message ?: "Unknown error"
        }
        createErrorDialog(msg)
    }

    private fun createProgressDialog() =
        AlertDialog.Builder(this, R.style.progressDialog)
            .setView(this.layoutInflater.inflate(R.layout.dialog_fragment_progress, null))
            .setCancelable(true)
            .create()

    private fun createErrorDialog(string: String) =
        AlertDialog.Builder(this)
            .setMessage(string)
            .setPositiveButton(getString(R.string.errorAcceptence)) { _, _ -> }
            .create()
            .show()

    override fun startLoading() {
        progressDialog?.show()
    }

    override fun endLoading() {
        progressDialog?.dismiss()
    }

}