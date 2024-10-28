package extensions.core

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ReactiveProperty<T>(
    value : T,
): ReadWriteProperty<Any?, T> {

    private var onChangeListener: (T) -> Unit = {}
    private var _value: T = value
    var value: T get() = _value
        set(value) {
            _value = value
            onChangeListener(_value)
        }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = _value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    fun onChange(block: (T) -> Unit) {
        onChangeListener = block
        onChangeListener(_value)
    }
}