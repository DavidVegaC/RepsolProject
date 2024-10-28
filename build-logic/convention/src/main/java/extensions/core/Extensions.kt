package extensions.core

fun ReactiveProperty<String>.ifNotBlank(block: (String) -> Unit) {
    onChange {
        if (it.isNotBlank()) {
            block(it)
        }
    }
}

fun ReactiveProperty<Boolean>.ifTrue(block: () -> Unit) {
    onChange {
        if (it) {
            block()
        }
    }
}

fun <T> ReactiveProperty<T>.lazyOnChange(block: (T) -> Unit) {
    var isUpdate = false
    onChange {
        if (isUpdate) {
            block(it)
        } else {
            isUpdate = true
        }
    }
}