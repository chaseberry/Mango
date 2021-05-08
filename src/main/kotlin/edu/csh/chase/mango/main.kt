package edu.csh.chase.mango

import java.util.*

fun main() {

    //Client("Clint Eastwood").insert()

    val r = Client().select {
        id = "123"
    }.edit {
        name = "Bob Marley"
        device.lastSeen = Date()
    }

}
