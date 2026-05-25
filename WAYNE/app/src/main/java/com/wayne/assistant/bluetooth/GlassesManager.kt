package com.wayne.assistant.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

@SuppressLint("MissingPermission")
class GlassesManager {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun getPairedDevices(): Set<BluetoothDevice> {
        return bluetoothAdapter?.bondedDevices ?: emptySet()
    }

    fun findRayBanMetaGlasses(): BluetoothDevice? {
        return getPairedDevices().firstOrNull { device ->
            val name = device.name ?: return@firstOrNull false
            name.contains("Ray-Ban", ignoreCase = true) ||
                name.contains("Meta", ignoreCase = true) ||
                name.contains("Glasses", ignoreCase = true)
        }
    }

    fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter?.isEnabled == true
    }
}
