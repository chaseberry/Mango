package edu.csh.chase.mango

fun <T : MangoObject> T.select(selector: T.() -> Unit): MongoOperation.Select<T> {
    selector(this)
    return MongoOperation.Select(this)
}

fun <T : MangoObject, U : MangoObject> T.select(u: List<U>, selector: Selector<U>.() -> Unit): MongoOperation<U> {
    return u.first().select2(selector)
}

fun <T : MangoObject> T.select2(selector: Selector<T>.() -> Unit): MongoOperation<T> {
    val s = Selector<T>()
    s.selector()
    s.select ?: throw IllegalArgumentException("eh?")

    s.select?.invoke(this)!!

    return if (s.edit == null) {
        MongoOperation.Select(this)
    } else {
        val r = readActions()
        s.edit!!.invoke(this)
        MongoOperation.Edit(this, r)
    }
}

fun <T : MangoObject> List<T>.select(selector: T.() -> Unit): MongoOperation.Select<T> {
    selector(first())
    return MongoOperation.Select(first())
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
