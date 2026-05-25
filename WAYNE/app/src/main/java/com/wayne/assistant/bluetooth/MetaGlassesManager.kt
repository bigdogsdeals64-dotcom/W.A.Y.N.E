package com.wayne.assistant.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

@SuppressLint("MissingPermission")
class MetaGlassesManager {
    private val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun pairedDevices(): Set<BluetoothDevice> {
        return adapter?.bondedDevices ?: emptySet()
    }

    fun findMetaGlasses(): BluetoothDevice? {
        return pairedDevices().firstOrNull { device ->
            device.name?.contains("Meta", ignoreCase = true) == true ||
                device.name?.contains("Ray-Ban", ignoreCase = true) == true
        }
    }
}
