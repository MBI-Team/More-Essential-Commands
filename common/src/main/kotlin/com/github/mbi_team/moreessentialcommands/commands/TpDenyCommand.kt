package com.github.mbi_team.moreessentialcommands.commands

import com.github.mbi_team.moreessentialcommands.utils.tpaRequestManager
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component

class TpDenyCommand : BaseCommand() {
    override val name = "tpdeny"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /tpdeny
            .executes { context ->
                val target = context.source.player
                if (target != null) {
                    val request = tpaRequestManager.getLatestRequest(target)
                    if (request != null) {
                        val sender = request.sender
                        
                        sender.sendSystemMessage(Component.literal("Teleport request denied by ${target.name.string}"))
                        target.sendSystemMessage(Component.literal("Denied teleport request from ${sender.name.string}"))
                        
                        tpaRequestManager.removeRequest(target)
                        1
                    } else {
                        target.sendSystemMessage(Component.literal("No pending teleport requests"))
                        0
                    }
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
}
