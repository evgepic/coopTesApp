package com.example.cooptesapp.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cooptesapp.R
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ErrorHandler, LoadingAction {

    private val viewmodel: MainViewModel by viewModels()
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val barcodeDao = (application as DataBaseInstance).getDataBaseInstance().barcodeDao()
        val unitDao = (application as DataBaseInstance).getDataBaseInstance().unitDao()
        val packDao = (application as DataBaseInstance).getDataBaseInstance().packDao()
        val packPriceDao = (application as DataBaseInstance).getDataBaseInstance().packPriceDao()
        val dbRep = DataBaseRepositoryInstance(barcodeDao, packDao, packPriceDao, unitDao)

        // -> comment after 1rst setUp
       //val list = DBHelper.createBDCollection()
        //viewmodel.insert(dbRep, list)
        //
    }

    override fun handle(throwable: Throwable) {
        var msg = throwable.message.toString()
        when (throwable) {
            is InputValidation.EmptyField -> msg = "Пустое поле"
            is InputValidation.EmptyPasswordField -> msg = "Пустое поле пароль"
            is InputValidation.WeakPass -> msg =
                "Пароль должен быть больше 6 символов"

            is InputValidation.WrongEmail -> msg = "Некорректный email"
            is InputValidation.UserNotExist -> msg = "Пользователя не существует"
            is InputValidation.PassNotCompare -> msg = "Пароли не совпадают"
        }
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("Понятно") { _, _ -> }
            .create()
            .show()
    }

    override fun startLoading() {

    }

    override fun endLoading() {

    }

}