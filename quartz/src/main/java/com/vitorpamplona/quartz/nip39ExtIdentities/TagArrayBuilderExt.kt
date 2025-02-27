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
package com.vitorpamplona.quartz.nip39ExtIdentities

import com.vitorpamplona.quartz.nip01Core.core.TagArrayBuilder
import com.vitorpamplona.quartz.nip01Core.metadata.MetadataEvent

fun TagArrayBuilder<MetadataEvent>.claims(identities: List<IdentityClaimTag>) = addAll(identities.map { it.toTagArray() })

fun TagArrayBuilder<MetadataEvent>.twitterClaim(twitter: TwitterIdentity) = add(twitter.toTagArray())

fun TagArrayBuilder<MetadataEvent>.mastodonClaim(mastodon: MastodonIdentity) = add(mastodon.toTagArray())

fun TagArrayBuilder<MetadataEvent>.githubClaim(github: GitHubIdentity) = add(github.toTagArray())

fun TagArrayBuilder<MetadataEvent>.twitterClaim(twitterUrl: String) = TwitterIdentity.parseProofUrl(twitterUrl)?.let { twitterClaim(it) }

fun TagArrayBuilder<MetadataEvent>.mastodonClaim(mastodonUrl: String) = MastodonIdentity.parseProofUrl(mastodonUrl)?.let { mastodonClaim(it) }

fun TagArrayBuilder<MetadataEvent>.githubClaim(githubUrl: String) = GitHubIdentity.parseProofUrl(githubUrl)?.let { githubClaim(it) }
