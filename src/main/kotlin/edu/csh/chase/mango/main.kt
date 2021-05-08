package edu.csh.chase.mango

import java.util.*

fun main() {

    //Client("Clint Eastwood").insert()

    Client().find {
        id = "123"
    }

    Client().edit(
        where = {
            id = "123"
        },
        edit = {
            name = "Bob Barley"
        }
    )

    val r = Client().select {
        id = "123"
    }.edit {
        name = "Bob Marley"
        select(devices) {
            select = {
                version = "1.0.0"
            }
            edit = {
                lastSeen = Date()
            }
        }
    }

    Client().select2 {
        select = {
            id = "123"
        }
        edit = {
            name = "Bob Marley"

        }
    }

}
