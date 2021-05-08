package edu.csh.chase.mango

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class MangoField<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        return value ?: throw IllegalArgumentException("WRONG")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        this.value = value
    }

}

class MangoSubobject<T : MangoObject>(val clz: KClass<T>) {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = clz.constructors.first().call()
        }

        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        return value ?: throw IllegalArgumentException("WRONG")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        this.value = value
    }
}

class MangoList<T : Any>(val clz: KClass<T>) {
    private var value: List<T>? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        if (value == null) {
            value = listOf(clz.constructors.first().call())
        }

        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        return value ?: throw IllegalArgumentException("WRONG")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: List<T>) {
        (thisRef as? MangoObject)?.onInteraction(property.name, value)
        this.value = value
    }
}
