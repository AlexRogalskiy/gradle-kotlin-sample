package io.nullables.api.playground.patterns.singleton2

/**
 * Created by Inno Fang on 2017/8/12.
 */
class ThreadSafeStaticInnerClassSingleton {
    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder {
        val instance = ThreadSafeStaticInnerClassSingleton()
    }
}
