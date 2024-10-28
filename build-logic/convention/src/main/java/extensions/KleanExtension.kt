package extensions

import extensions.core.ReactiveProperty
import org.gradle.kotlin.dsl.provideDelegate

open class KleanExtension {

    internal val nameProperty = ReactiveProperty(value = "")
    internal val featuresExtensions = ReactiveProperty(
        value = KleanFeaturesExtension()
    )

    var name: String by nameProperty

    fun features(block: KleanFeaturesExtension.() -> Unit) {
        featuresExtensions.value = featuresExtensions.value.apply(block)
    }

    companion object {
        const val NAME = "klean"
    }
}