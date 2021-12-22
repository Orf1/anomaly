package gg.scala.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectOutputStream
import java.net.ServerSocket


class AnomalyServer {
    private val port = 5000
    private val pythonServerSocket: ServerSocket = ServerSocket(port)

    init {
        connectPython()
    }

    private fun connectPython() {
        println("Starting python server thread.")
        println("Python server thread listening for connections.")
        while (true) {
            val socket = pythonServerSocket.accept()
            println("Client connected!")
            val inFromClient = BufferedReader(InputStreamReader(socket.getInputStream()))
            val outToClient = socket.getOutputStream().bufferedWriter()
            outToClient.write("Test")
            while (true) {
                val incoming = inFromClient.readLine()
                println("Received: $incoming")
                if (incoming.equals("q")) {
                    socket.close()
                    break
                }
            }
        }

    }
}

fun main() {
    AnomalyServer()
}
