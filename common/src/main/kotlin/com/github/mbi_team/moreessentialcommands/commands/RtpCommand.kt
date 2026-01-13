package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Mth
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkStatus

class RtpCommand : BaseCommand() {
    override val name = "rtp"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /rtp
            .executes { context ->
                val player = context.source.player
                if (player != null) {
                    val success = teleportRandomly(player)
                    if (success) {
                        context.source.sendSystemMessage(Component.literal("Teleported to random location"))
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("Failed to find safe location after multiple attempts"))
                        0
                    }
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
    
    private fun teleportRandomly(player: ServerPlayer): Boolean {
        val level = player.level
        val maxAttempts = 20
        val radius = 1000
        
        for (attempt in 0 until maxAttempts) {
            // Generate random coordinates
            val x = player.blockX + (Mth.random() * radius * 2 - radius).toInt()
            val z = player.blockZ + (Mth.random() * radius * 2 - radius).toInt()
            
            // Get the highest block at this position
            val chunk = level.getChunk(x shr 4, z shr 4, ChunkStatus.FULL)
            val y = level.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, x, z)
            
            // Check if the location is safe
            if (isSafeLocation(level, x, y, z)) {
                player.teleportTo(level, x.toDouble() + 0.5, y.toDouble(), z.toDouble() + 0.5, player.yRot, 0.0f)
                return true
            }
        }
        
        return false
    }
    
    private fun isSafeLocation(level: Level, x: Int, y: Int, z: Int): Boolean {
        // Check if y is above 64
        if (y < 64) return false
        
        // Check if the block is solid and not dangerous
        val feetBlock = level.getBlockState(net.minecraft.core.BlockPos(x, y, z))
        val headBlock = level.getBlockState(net.minecraft.core.BlockPos(x, y + 1, z))
        val belowBlock = level.getBlockState(net.minecraft.core.BlockPos(x, y - 1, z))
        
        return isSafeBlock(belowBlock) && isSafeBlock(feetBlock) && isSafeBlock(headBlock)
    }
    
    private fun isSafeBlock(state: BlockState): Boolean {
        return when (state.block) {
            Blocks.AIR, Blocks.GRASS, Blocks.DIRT, Blocks.STONE, Blocks.SAND, Blocks.GRAVEL -> true
            Blocks.WATER, Blocks.LAVA, Blocks.CACTUS, Blocks.MAGMA_BLOCK, Blocks.FIRE -> false
            else -> state.isSolid && !state.isLiquid && !state.isAir
        }
    }
}
