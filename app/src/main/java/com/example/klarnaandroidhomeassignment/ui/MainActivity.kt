package com.example.klarnaandroidhomeassignment.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.klarnaandroidhomeassignment.R
import com.example.klarnaandroidhomeassignment.databinding.ActivityMainBinding
import com.example.klarnaandroidhomeassignment.model.WeatherResponse
import com.example.klarnaandroidhomeassignment.viewModel.WeatherViewModel
import com.mayowa.android.locationwithlivedata.GpsUtils


class MainActivity : AppCompatActivity() {

    private var isGPSEnabled = false
    var weatherViewModel: WeatherViewModel? = null
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        binding.mainViewModel = weatherViewModel
        binding.setLifecycleOwner(this);

        GpsUtils(this).turnGPSOn(object : GpsUtils.OnGpsListener {

            override fun gpsStatus(isGPSEnable: Boolean) {
                this@MainActivity.isGPSEnabled = isGPSEnable
            }
        })
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    private fun invokeLocationAction() {
        when {
            !isGPSEnabled -> Toast.makeText(
                applicationContext,
                "GPS not Enabled",
                Toast.LENGTH_SHORT
            ).show()

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> Toast.makeText(
                applicationContext,
                "Permission Request",
                Toast.LENGTH_SHORT
            ).show()

            else -> ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_REQUEST
            )
        }
    }

    private fun startLocationUpdate() {
        weatherViewModel?.getLocationData()?.observe(this, Observer {
            weatherViewModel!!.init((it.latitude).toBigDecimal().toPlainString() + "," + (it.longitude).toBigDecimal().toPlainString())

            weatherViewModel!!.getWeatherRepository()
                ?.observe(this, Observer { weatherResponse : WeatherResponse ->
                    binding?.invalidateAll()

                })
        })
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() =
        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }
}

const val LOCATION_REQUEST = 100
const val GPS_REQUEST = 101




