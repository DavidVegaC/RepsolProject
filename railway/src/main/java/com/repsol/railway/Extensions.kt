package com.repsol.railway

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <T, E> Output<T, E>.isSuccessful(): Boolean {
    contract {
        returns(true) implies (this@isSuccessful is Output.Success)
        returns(false) implies (this@isSuccessful is Output.Failure)
    }
    return this is Output.Success
}

fun <T, E> Output<T, E>.asSuccessful(): Output.Success<T> {
    return this as Output.Success
}

@OptIn(ExperimentalContracts::class)
fun <T, E> Output<T, E>.isFailure(): Boolean {
    contract {
        returns(false) implies (this@isFailure is Output.Success)
        returns(true) implies (this@isFailure is Output.Failure)
    }
    return this !is Output.Success
}

fun <T, E> Output<T, E>.asFailure(): Output.Failure<E> {
    return this as Output.Failure
}

inline fun <I1, I2, O1> Output<I1, I2>.transform(transformer: (I1)-> O1): Output<O1, I2> {
    return when(this) {
        is Output.Success -> Output.Success(data = transformer(data))
        is Output.Failure -> this
    }
}

fun <I1, I2, O2> Output<I1, I2>.transformFailure(transformer: (I2)-> O2): Output<I1, O2> {
    return when(this) {
        is Output.Success -> this
        is Output.Failure -> Output.Failure(error = transformer(error))
    }
}

fun <I1, I2, O1> Output<List<I1>, I2>.map(mapper: (I1)->O1): Output<List<O1>, I2> {
    return when(this) {
        is Output.Success -> Output.Success(data = data.map(mapper))
        is Output.Failure -> this
    }
}

inline fun <I1, I2> Output<List<I1>, I2>.filter(predicate: (I1) -> Boolean): Output<List<I1>, I2> {
    return when(this) {
        is Output.Success -> Output.Success(data = data.filter(predicate))
        is Output.Failure -> this
    }
}

fun <I, O> Output<I, O>.toUnit(): Output<Unit, O> {
    return this.transform { }
}

inline fun <I, O> Output<I, O>.onComplete(block: (Output<I, O>) -> Unit): Output<I, O> {
    block(this)
    return this
}