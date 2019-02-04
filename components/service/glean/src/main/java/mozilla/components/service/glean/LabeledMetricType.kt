/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.service.glean

import mozilla.components.support.base.log.logger.Logger

/**
 * This implements the developer facing API for labeled metrics.
 *
 * Instances of this class type are automatically generated by the parsers at build time,
 * allowing developers to record values that were previously registered in the metrics.yaml file.
*/

data class LabeledMetricType<T>(
    override val disabled: Boolean,
    override val category: String,
    override val lifetime: Lifetime,
    override val name: String,
    override val sendInPings: List<String>,
    val subMetric: T,
    val labels: List<String>?
) : CommonMetricData<LabeledMetricType<T>> {

    override val defaultStorageDestinations: List<String> = listOf("metrics")

    private val logger = Logger("glean/LabeledMetricType")

    fun get(label: String): T {
        return (subMetric as CommonMetricData<T>).getWithName("#$name#$label")
    }
}
