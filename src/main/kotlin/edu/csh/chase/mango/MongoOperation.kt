package edu.csh.chase.mango

sealed class MongoOperation<T : MangoObject>(protected val obj: T) {

    class Select<T : MangoObject>(obj: T) : MongoOperation<T>(obj) {
        val selection = obj.readActions()

        fun get(): T {
            println("db.${obj.collection}.get($selection)")
            return obj
        }

        fun edit(edit: T.() -> Unit): Edit<T> {
            edit.invoke(obj)
            return Edit(obj, selection)
        }
    }

    class Edit<T : MangoObject>(obj: T, val select: Map<String, Any?>) : MongoOperation<T>(obj) {
        val edits = obj.readActions()

        init {
            println("db.${obj.collection}.update($select, $edits)")
        }
    }

}
