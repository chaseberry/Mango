package edu.csh.chase.mango

abstract class MangoObject(val collection: String) {

    private val map = HashMap<String, Any?>()

    internal fun onInteraction(field: String, value: Any?) {
        map[field] = value
    }

    fun readActions(): Map<String, Any?> {
        val l = map.toMap()
        map.clear()

        return l.doThing()
    }

}
