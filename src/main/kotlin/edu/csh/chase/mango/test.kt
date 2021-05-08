package edu.csh.chase.mango

import java.util.*

class Client() : MangoObject("clients") {

    var id: String by MangoField()

    var name: String by MangoField()

    var device by MangoSubobject(Device::class)

    constructor(name: String) : this() {
        this.name = name
    }
}

class Device() : MangoObject("???") {

    var name by MangoField<String>()

    var version by MangoField<String>()

    var lastSeen by MangoField<Date>()

}

