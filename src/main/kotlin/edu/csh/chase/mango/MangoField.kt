package edu.csh.chase.mango

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class MangoField<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        (thisRef as? MangoObject)?.onGet(property.name)
        return value ?: throw IllegalArgumentException("WRONG")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if ((thisRef as? MangoObject)?.onSet(property.name, value) == true) {
            this.value = value
        }
    }

}

class MangoSubobject<T: MangoObject>(val clz: KClass<T>) {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = clz.constructors.first().call()
        }

        (thisRef as? MangoObject)?.onGet(property.name, value!!)
        return value ?: throw IllegalArgumentException("WRONG")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if ((thisRef as? MangoObject)?.onSet(property.name, value) == true) {
            this.value = value
        }
    }
}
