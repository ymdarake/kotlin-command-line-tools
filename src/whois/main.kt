package whois

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket


fun main(args: Array<String>) {
	while (true) {
		val clientInput: String? = readLine()
		clientInput?.let {
			exists(it)
		}?.let {
			println(mark(it))
		}
	}
}

fun mark(exists: Boolean) = if (exists) "NG" else "OK"

// TODO: Kotlin code
fun exists(domain: String): Boolean {
	val socket = Socket("com.whois-servers.net", 43)
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
	writer.write(domain + "\r\n\r\n")
	writer.flush()

	var exists = true
	reader.readLines().forEach {
		if (it.toLowerCase().contains("no match")) {
			exists = false
		}
	}
	reader.close()
	writer.close()
	socket.close()
	return exists
}
