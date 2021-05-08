package edu.csh.chase.mango

class Selector<T : MangoObject> {

    var select: (T.() -> Unit)? = null

    var edit: (T.() -> Unit)? = null
}
