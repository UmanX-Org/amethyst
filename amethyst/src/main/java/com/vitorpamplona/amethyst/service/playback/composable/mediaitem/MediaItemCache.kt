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
package com.vitorpamplona.amethyst.service.playback.composable.mediaitem

import android.os.Bundle
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.vitorpamplona.amethyst.commons.compose.GenericBaseCache
import kotlin.coroutines.cancellation.CancellationException

class MediaItemCache : GenericBaseCache<MediaItemData, LoadedMediaItem>(20) {
    override suspend fun compute(key: MediaItemData): LoadedMediaItem =
        LoadedMediaItem(
            key,
            MediaItem
                .Builder()
                .setMediaId(key.videoUri)
                .setUri(key.videoUri)
                .setMediaMetadata(
                    MediaMetadata
                        .Builder()
                        .setArtist(key.authorName?.ifBlank { null })
                        .setTitle(key.title?.ifBlank { null } ?: key.videoUri)
                        .setExtras(
                            Bundle().apply {
                                putString("callbackUri", key.callbackUri)
                            },
                        ).setArtworkUri(
                            try {
                                key.artworkUri?.toUri()
                            } catch (e: Exception) {
                                if (e is CancellationException) throw e
                                null
                            },
                        ).build(),
                ).build(),
        )
}
