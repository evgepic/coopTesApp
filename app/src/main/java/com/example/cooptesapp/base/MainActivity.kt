package com.example.cooptesapp.base

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