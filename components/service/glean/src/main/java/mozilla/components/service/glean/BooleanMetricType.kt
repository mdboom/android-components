/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.service.glean

import kotlinx.coroutines.launch
import mozilla.components.service.glean.storages.BooleansStorageEngine
import mozilla.components.support.base.log.logger.Logger

/**
 * This implements the developer facing API for recording boolean metrics.
 *
 * Instances of this class type are automatically generated by the parsers at build time,
 * allowing developers to record values that were previously registered in the metrics.yaml file.
 *
 * The boolean API only exposes the [set] method.
 */
data class BooleanMetricType(
    override val disabled: Boolean,
    override val category: String,
    override val lifetime: Lifetime,
    override val name: String,
    override val sendInPings: List<String>
) : CommonMetricData<BooleanMetricType> {

    override val defaultStorageDestinations: List<String> = listOf("metrics")

    private val logger = Logger("glean/BooleanMetricType")

    /**
     * Set a boolean value.
     *
     * @param value This is a user defined boolean value.
     */
    fun set(value: Boolean) {
        // TODO report errors through other special metrics handled by the SDK. See bug 1499761.

        if (!shouldRecord(logger)) {
            return
        }

        Dispatchers.API.launch {
            // Delegate storing the boolean to the storage engine.
            BooleansStorageEngine.record(
                this@BooleanMetricType,
                value = value
            )
        }
    }

    override fun getWithName(newName: String): BooleanMetricType {
        return BooleanMetricType(
            name = newName,
            category = category,
            lifetime = lifetime,
            disabled = disabled,
            sendInPings = sendInPings

        )
    }
}
