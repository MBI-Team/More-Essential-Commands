package com.github.mbi_team.moreessentialcommands.utils

import net.minecraft.server.level.ServerPlayer
import java.util.*

class TpaRequestManager {
    data class TpaRequest(val sender: ServerPlayer, val target: ServerPlayer, val timestamp: Long)
    
    private val requests: MutableMap<UUID, TpaRequest> = mutableMapOf()
    private val maxRequestAge = 60000L // 60 seconds
    
    fun addRequest(sender: ServerPlayer, target: ServerPlayer) {
        requests[target.uuid] = TpaRequest(sender, target, System.currentTimeMillis())
    }
    
    fun getLatestRequest(target: ServerPlayer): TpaRequest? {
        val request = requests[target.uuid]
        if (request != null && System.currentTimeMillis() - request.timestamp > maxRequestAge) {
            requests.remove(target.uuid)
            return null
        }
        return request
    }
    
    fun removeRequest(target: ServerPlayer) {
        requests.remove(target.uuid)
    }
    
    fun cleanupExpiredRequests() {
        val now = System.currentTimeMillis()
        requests.entries.removeIf { (_, request) -> now - request.timestamp > maxRequestAge }
    }
}

// Global instance
val tpaRequestManager = TpaRequestManager()
