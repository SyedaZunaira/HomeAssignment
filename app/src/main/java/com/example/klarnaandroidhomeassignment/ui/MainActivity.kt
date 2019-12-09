package com.example.klarnaandroidhomeassignment.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.klarnaandroidhomeassignment.R
import com.example.klarnaandroidhomeassignment.base.BaseActivity
import com.example.klarnaandroidhomeassignment.databinding.ActivityMainBinding
import com.example.klarnaandroidhomeassignment.utils.LOCATION_REQUEST
import com.example.klarnaandroidhomeassignment.viewModel.WeatherViewModel

class MainActivity : BaseActivity() {

    private var weatherViewModel: WeatherViewModel? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        this.binding.mainViewModel = weatherViewModel
        this.binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun invokeLocationAction() {
        when {
            !isGPSEnabled -> Toast.makeText(
                applicationContext,
                getString(R.string.no_gps),
                Toast.LENGTH_SHORT
            ).show()

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> Toast.makeText(
                applicationContext,
                getString(R.string.permissions_not_granted),
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
                ?.observe(this, Observer {
                    binding.invalidateAll()
                })
        })
    }


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






