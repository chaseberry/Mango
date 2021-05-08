package edu.csh.chase.mango

abstract class MangoObject(val collection: String) {

    private val map = HashMap<String, Any?>()

    internal fun onGet(field: String) {
        map[field] = "getter"
    }

    internal fun onGet(field: String, obj: MangoObject) {
        map[field] = obj
    }

    internal fun onSet(field: String, value: Any?): Boolean {
        map[field] = value
        return true
    }

    fun readActions(): Map<String, Any?> {
        val l = map.toMap()
        map.clear()

        return l.doThing()
    }

}
