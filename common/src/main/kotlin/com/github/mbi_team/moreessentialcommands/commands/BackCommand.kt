package com.github.mbi_team.moreessentialcommands.commands

import com.github.mbi_team.moreessentialcommands.utils.positionHistoryManager
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component

class BackCommand : BaseCommand() {
    override val name = "back"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /back
            .executes { context ->
                val player = context.source.player
                if (player != null) {
                    val lastPos = positionHistoryManager.getLastPosition(player)
                    if (lastPos != null) {
                        // Save current position before teleporting
                        positionHistoryManager.savePosition(player)
                        
                        // Teleport to last position
                        player.teleportTo(
                            player.level,
                            lastPos.x,
                            lastPos.y,
                            lastPos.z,
                            lastPos.yaw,
                            lastPos.pitch
                        )
                        
                        context.source.sendSystemMessage(Component.literal("Teleported to last position"))
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("No previous position found"))
                        0
                    }
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
}
