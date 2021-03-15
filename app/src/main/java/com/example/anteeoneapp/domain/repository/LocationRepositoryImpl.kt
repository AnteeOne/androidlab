package com.example.anteeoneapp.domain.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.anteeoneapp.data.Coordinates
import com.example.anteeoneapp.domain.repository.interfaces.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.NullPointerException
import kotlin.coroutines.resumeWithException

object LocationRepositoryImpl:
    LocationRepository {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    private val STANDART_LATITUDE = 54.550546
    private val STANDART_LONGITUDE = 53.602365

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(context: Context): Coordinates = suspendCancellableCoroutine { cont ->
        var result = Coordinates(STANDART_LATITUDE, STANDART_LONGITUDE)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    result = Coordinates(location.latitude, location.longitude)
                    cont.resume(result){
                        cont.resumeWithException(it)
                    }
                    Log.println(Log.INFO,"anteetag","GPS is available")
                }
                else{
                    Toast.makeText(context,"GPS isn't available!",Toast.LENGTH_SHORT).show()
                    Log.println(Log.INFO,"anteetag","GPS is not available")
                    cont.resumeWithException(NullPointerException())
                }
            }.addOnFailureListener {

            }
    }

    override fun getDefaultLocation(): Coordinates {
        return Coordinates(STANDART_LATITUDE, STANDART_LONGITUDE)
    }

    fun initFusedClient(context: Context){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

}