package io.nullables.api.playground.patterns.factory_method

/**
 * Created by Inno Fang on 2017/9/1.
 */
class CakeFactory : Factory() {

    override fun <T : Cake> createProduct(clz: Class<T>): T? {
        var cake: Cake? = null
        try {
            cake = Class.forName(clz.name).getConstructor().newInstance() as Cake
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cake as T?
    }
}
