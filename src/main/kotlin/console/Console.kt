package console

import config.ConfigLoader
import models.KelasModel
import spammer.LoveSpammer
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class EdumekConsole: Runnable {
    companion object {
        public val shared = EdumekConsole()
    }
    lateinit var classes: Array<KelasModel>
    private  var thread: Thread? = null
    private fun startConsole(buffer: BufferedReader) {
        print("Console % ")
        var terminal = buffer.readLine()
        if (terminal == null) {
            buffer.close()
            return
        }
        if (terminal.equals("help")) {
            println("status - check current status")
            println("shutdown/quit - exit program")
            println("start - start program")
        }
        if (terminal.equals("start")) {
            ConfigLoader.shared.loadConfig()
            val spammer = LoveSpammer()
            println("type 'quit' to exit. Now Logging...")
            if (!(::classes.isInitialized)) {
                this.classes = spammer.getClassesList(ConfigLoader.config.bearer)
            }

        }
        if (terminal.equals("shutdown") || terminal.equals("quit")) {
            println("Thank you for using Edumek")
            return
        }
        this.startConsole(buffer)
    }

    private fun prepareConsole() {
        println("Type 'help' to show command list.")
        var buffer = BufferedReader(InputStreamReader(System.`in`, Charset.forName("UTF-8")))
        this.startConsole(buffer)
    }

    public fun init() {
        if (this.thread != null) {
            throw UnsupportedOperationException("Console already started.");
        }
        this.thread = Thread(this)
        this.thread!!.isDaemon = false
        this.thread!!.start()
    }

    override fun run() {
        this.prepareConsole()
    }
}