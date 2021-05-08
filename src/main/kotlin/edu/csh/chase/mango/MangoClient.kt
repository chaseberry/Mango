package edu.csh.chase.mango

import kotlin.reflect.KClass

class MangoClient {

    private val cacheMap = HashMap<KClass<out MangoObject>, String>()

    fun <T : MangoObject> register(mango: KClass<T>) {
        if (mango in cacheMap) {
            return
        }

        cacheMap[mango] = ""
    }


}
