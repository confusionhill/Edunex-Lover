package config

import com.google.gson.Gson
import models.ConfigModel
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


class ConfigLoader {
    val gson = Gson()
    companion object {
        lateinit var config: ConfigModel
        val shared = ConfigLoader()
    }
    fun loadConfig() {
        try {
            print("\nLoading..")
            println()
            val configFile = "./config/config.json"
            if (!File(configFile).exists()) {
                throw FileNotFoundException()
            }
            val stream = FileInputStream(configFile)
            val jsonStr = IOUtils.toString(stream, "UTF-8")
            val config = gson.fromJson(jsonStr, ConfigModel::class.java)
            ConfigLoader.config = config
        }
        catch (e: FileNotFoundException) {

        }
        catch (e: Exception) {

        }
    }
}