/**
 * Copyright (c) 2024 Vitor Pamplona
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.vitorpamplona.amethyst.ui.screen.loggedIn.relays.common

import androidx.compose.runtime.Immutable
import com.vitorpamplona.ammolite.relays.RelayBriefInfoCache
import com.vitorpamplona.ammolite.relays.RelayStats
import com.vitorpamplona.quartz.nip01Core.relay.RelayStat
import com.vitorpamplona.quartz.nip65RelayList.RelayUrlFormatter

@Immutable
data class BasicRelaySetupInfo(
    val url: String,
    val relayStat: RelayStat,
    val paidRelay: Boolean = false,
) {
    val briefInfo: RelayBriefInfoCache.RelayBriefInfo = RelayBriefInfoCache.RelayBriefInfo(url)
}

fun relaySetupInfoBuilder(url: String): BasicRelaySetupInfo {
    val normalized = RelayUrlFormatter.normalize(url)
    return BasicRelaySetupInfo(
        normalized,
        RelayStats.get(normalized),
    )
}
