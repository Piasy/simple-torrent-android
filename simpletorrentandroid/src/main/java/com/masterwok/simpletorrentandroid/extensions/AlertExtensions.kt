package com.masterwok.simpletorrentandroid.extensions

import com.frostwire.jlibtorrent.alerts.Alert
import com.frostwire.jlibtorrent.alerts.AlertType.BLOCK_DOWNLOADING
import com.frostwire.jlibtorrent.alerts.AlertType.BLOCK_FINISHED
import com.frostwire.jlibtorrent.alerts.AlertType.BLOCK_TIMEOUT
import com.frostwire.jlibtorrent.alerts.AlertType.BLOCK_UPLOADED
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_ANNOUNCE
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_DIRECT_RESPONSE
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_ERROR
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_GET_PEERS
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_GET_PEERS_REPLY
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_IMMUTABLE_ITEM
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_LIVE_NODES
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_OUTGOING_GET_PEERS
import com.frostwire.jlibtorrent.alerts.AlertType.DHT_REPLY
import com.frostwire.jlibtorrent.alerts.AlertType.INCOMING_CONNECTION
import com.frostwire.jlibtorrent.alerts.AlertType.INCOMING_REQUEST
import com.frostwire.jlibtorrent.alerts.AlertType.LISTEN_FAILED
import com.frostwire.jlibtorrent.alerts.AlertType.LISTEN_SUCCEEDED
import com.frostwire.jlibtorrent.alerts.AlertType.LSD_PEER
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_BAN
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_BLOCKED
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_CONNECT
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_DISCONNECTED
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_ERROR
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_LOG
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_SNUBBED
import com.frostwire.jlibtorrent.alerts.AlertType.PEER_UNSNUBBED
import com.frostwire.jlibtorrent.alerts.AlertType.PERFORMANCE
import com.frostwire.jlibtorrent.alerts.AlertType.PORTMAP
import com.frostwire.jlibtorrent.alerts.AlertType.TRACKER_ANNOUNCE
import com.frostwire.jlibtorrent.alerts.AlertType.TRACKER_ERROR
import com.frostwire.jlibtorrent.alerts.AlertType.TRACKER_REPLY
import com.frostwire.jlibtorrent.alerts.AlertType.TRACKER_WARNING
import com.frostwire.jlibtorrent.alerts.AlertType.UNWANTED_BLOCK
import com.frostwire.jlibtorrent.alerts.TorrentAlert

/**
 * Check if the [Alert] is of type [TorrentAlert].
 */
internal fun Alert<*>.isTorrentAlert() = this is TorrentAlert


/**
 * Check if the [Alert] has a valid [@see TorrentHandle].
 */
internal fun Alert<*>.hasValidTorrentHandle() = (this as? TorrentAlert)
        ?.handle()
        ?.isValid
        ?: false

internal fun Alert<*>.trackerAlert() = when (type()) {
        TRACKER_ANNOUNCE, TRACKER_REPLY, TRACKER_WARNING, TRACKER_ERROR -> true
        else -> false
}

internal fun Alert<*>.peerAlert() = when (type()) {
        LSD_PEER, PEER_BLOCKED, PEER_BAN, PEER_CONNECT, PEER_DISCONNECTED,
        PEER_ERROR, PEER_SNUBBED, PEER_UNSNUBBED, PEER_LOG -> true
        else -> false
}

internal fun Alert<*>.dhtAlert() = when (type()) {
        DHT_OUTGOING_GET_PEERS, DHT_GET_PEERS, DHT_GET_PEERS_REPLY,
        DHT_ERROR, DHT_ANNOUNCE, DHT_REPLY, DHT_DIRECT_RESPONSE,
        DHT_IMMUTABLE_ITEM, DHT_LIVE_NODES, /*DHT_STATS, DHT_BOOTSTRAP*/ -> true
        else -> false
}

internal fun Alert<*>.blockAlert() = when (type()) {
        BLOCK_DOWNLOADING, BLOCK_TIMEOUT, BLOCK_UPLOADED, BLOCK_FINISHED, UNWANTED_BLOCK -> true
        else -> false
}

internal fun Alert<*>.miscAlert() = when (type()) {
        PORTMAP, LISTEN_FAILED, LISTEN_SUCCEEDED, PERFORMANCE,
        INCOMING_CONNECTION, INCOMING_REQUEST -> true
        else -> false
}
