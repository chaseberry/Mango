package edu.csh.chase.mango

fun <T : MangoObject> T.select(selector: T.() -> Unit): MongoOperation.Select<T> {
    selector(this)
    return MongoOperation.Select(this)
}

fun <T : MangoObject> T.insert() {
    println("db.$collection.insert(${readActions()})")
}

internal fun Map<String, Any?>.doThing(): Map<String, Any?> {
    return this.mapValues {
        val v = it.value
        if (v is MangoObject) {
            v.readActions().doThing()
        } else {
            v
        }
    }
}
