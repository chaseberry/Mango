package edu.csh.chase.mango

sealed class Event {

    class Set(val field: String, val value: Any?) : Event()

    class Get(val field: String) : Event()

}
