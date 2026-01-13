package com.github.mbi_team.moreessentialcommands.utils

import net.minecraft.server.level.ServerPlayer
import java.util.*

class PositionHistoryManager {
    data class Position(val x: Double, val y: Double, val z: Double, val yaw: Float, val pitch: Float)
    
    private val playerPositions: MutableMap<UUID, Position> = mutableMapOf()
    
    fun savePosition(player: ServerPlayer) {
        val pos = Position(player.x, player.y, player.z, player.yRot, player.xRot)
        playerPositions[player.uuid] = pos
    }
    
    fun getLastPosition(player: ServerPlayer): Position? {
        return playerPositions[player.uuid]
    }
    
    fun removePosition(player: ServerPlayer) {
        playerPositions.remove(player.uuid)
    }
}

// Global instance
val positionHistoryManager = PositionHistoryManager()
