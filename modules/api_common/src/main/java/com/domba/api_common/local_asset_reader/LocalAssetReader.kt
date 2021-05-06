package com.domba.api_common.local_asset_reader

import android.content.Context
import com.domba.api_common.constant.EMPTY_STRING
import com.google.gson.Gson
import java.nio.charset.Charset

const val DUMMY_ACTIVE_MEASUREMENT_EVENT = "ActiveMeasurementEventDummyResponse.json"
const val DUMMY_ALL_MEASUREMENT_EVENT = "AllMeasurementEventDummyResponse.json"

class LocalAssetReaderImpl(val context: Context) : LocalAssetReader {

    val gson: Gson = Gson()

    override fun <T> readLocalAssetList(fileName: String, clazz: Class<Array<T>>): MutableList<T> {
        val json = loadJsonFromAsset(fileName, context)
        val array = Gson().fromJson(json, clazz)
        return array.toMutableList()
    }

    override fun <T> readLocalAsset(fileName: String, type: Class<T>): T {
        val json = loadJsonFromAsset(fileName, context)
        return gson.fromJson(json, type)
    }

    private fun loadJsonFromAsset(fileName: String, context: Context): String {
        var json = EMPTY_STRING

        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return json
    }
}

interface LocalAssetReader {
    fun <T>readLocalAsset(fileName: String, type: Class<T>): T
    fun <T> readLocalAssetList(fileName: String, clazz: Class<Array<T>>): MutableList<T>
}

