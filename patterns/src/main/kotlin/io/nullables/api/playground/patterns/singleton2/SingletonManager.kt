package io.nullables.api.playground.patterns.singleton2

import java.util.HashMap

/**
 * Created by Inno Fang on 2017/8/12.
 */
class SingletonManager {
    private val objectMap = HashMap<String, Any>()

    private fun SingletonManager() {}

    fun registerService(key: String, instance: Any) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance)
        }
    }

    fun getService(key: String): Any {
        return objectMap[key]!!
    }
}
