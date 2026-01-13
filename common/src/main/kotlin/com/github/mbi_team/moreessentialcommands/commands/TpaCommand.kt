package com.github.mbi_team.moreessentialcommands.commands

import com.github.mbi_team.moreessentialcommands.utils.tpaRequestManager
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

class TpaCommand : BaseCommand() {
    override val name = "tpa"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /tpa <player>
            .then(net.minecraft.commands.Commands.argument("target", EntityArgument.player())
                .executes { context ->
                    val sender = context.source.player
                    val target = EntityArgument.getPlayer(context, "target")
                    
                    if (sender != null && target != null) {
                        if (sender == target) {
                            sender.sendSystemMessage(Component.literal("You can't teleport to yourself"))
                            return@executes 0
                        }
                        
                        tpaRequestManager.addRequest(sender, target)
                        
                        sender.sendSystemMessage(Component.literal("Sent teleport request to ${target.name.string}"))
                        target.sendSystemMessage(Component.literal("${sender.name.string} has requested to teleport to you. Use /tpaccept to accept or /tpdeny to deny"))
                        
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                        0
                    }
                }
            )
    }
}
