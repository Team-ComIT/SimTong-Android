package com.comit.core_design_system.modifier

/**
 * 클릭이 지연될 시간을 정의하는 변수
 */
private const val ClickEventDelayTime: Long = 300L

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= ClickEventDelayTime) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}
