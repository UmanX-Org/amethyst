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
package com.vitorpamplona.quartz.utils.sha256

import android.util.Log
import java.security.MessageDigest
import java.util.concurrent.ArrayBlockingQueue

class Sha256Pool(
    size: Int,
) {
    private val pool = ArrayBlockingQueue<MessageDigest>(size)

    private fun digest() = MessageDigest.getInstance("SHA-256")

    init {
        repeat(size) {
            pool.add(digest())
        }
    }

    private fun acquire(): MessageDigest {
        if (pool.size < 1) {
            Log.w("SHA256Pool", "Pool running low in available digests")
        }
        return pool.take()
    }

    private fun release(digest: MessageDigest) {
        digest.reset()
        pool.put(digest)
    }

    fun hash(byteArray: ByteArray): ByteArray {
        val digest = acquire()
        try {
            return digest.digest(byteArray)
        } finally {
            release(digest)
        }
    }
}
