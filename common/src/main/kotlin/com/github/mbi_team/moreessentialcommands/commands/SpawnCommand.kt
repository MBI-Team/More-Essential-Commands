package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.Level

class SpawnCommand : BaseCommand() {
    override val name = "spawn"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /spawn [targets]
            .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                .executes { context ->
                    val targets = EntityArgument.getPlayers(context, "targets")
                    targets.forEach { player ->
                        teleportToSpawn(player)
                    }
                    context.source.sendSystemMessage(Component.literal("Teleported ${targets.size} players to spawn"))
                    targets.size
                }
            )
            // /spawn
            .executes { context ->
                val player = context.source.player
                if (player != null) {
                    teleportToSpawn(player)
                    context.source.sendSystemMessage(Component.literal("Teleported to spawn"))
                    1
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
    
    private fun teleportToSpawn(player: ServerPlayer) {
        val level = player.level
        val spawnPos = level.levelData.spawnPosition
        val spawnAngle = level.levelData.spawnAngle
        
        player.teleportTo(
            level as Level,
            spawnPos.x.toDouble() + 0.5,
            spawnPos.y.toDouble(),
            spawnPos.z.toDouble() + 0.5,
            spawnAngle,
            0.0f
        )
    }
}
