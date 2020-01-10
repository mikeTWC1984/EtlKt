package secret

import java.util.*
import kotlin.collections.HashMap

fun newCred(user: String, password: String): Properties {
    val cred = Properties()
    cred["username"] = user
    cred["password"] = password
    return cred
}

val cred: HashMap<String, Properties> = hashMapOf(
    "ctip" to newCred("telecom_reporting", "1233ty2"),
    "ctir" to newCred("telecom_reporting", "1234sdfd"),
    "ctid" to newCred("telecom_reporting", "1233sfzxs")
)
