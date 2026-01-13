package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.world.GameType
import net.minecraft.world.entity.player.Player

class GmCommand : BaseCommand() {
    override val name = "gm"
    override val permissionLevel = 2
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /gm <mode> [targets]
            .then(net.minecraft.commands.Commands.literal("s")
                .executes { context ->
                    changeGameMode(context, GameType.SURVIVAL)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.SURVIVAL, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("survival")
                .executes { context ->
                    changeGameMode(context, GameType.SURVIVAL)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.SURVIVAL, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("c")
                .executes { context ->
                    changeGameMode(context, GameType.CREATIVE)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.CREATIVE, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("creative")
                .executes { context ->
                    changeGameMode(context, GameType.CREATIVE)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.CREATIVE, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("a")
                .executes { context ->
                    changeGameMode(context, GameType.ADVENTURE)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.ADVENTURE, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("adventure")
                .executes { context ->
                    changeGameMode(context, GameType.ADVENTURE)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.ADVENTURE, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("sp")
                .executes { context ->
                    changeGameMode(context, GameType.SPECTATOR)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.SPECTATOR, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
            .then(net.minecraft.commands.Commands.literal("spectator")
                .executes { context ->
                    changeGameMode(context, GameType.SPECTATOR)
                }
                .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                    .executes { context ->
                        changeGameMode(context, GameType.SPECTATOR, EntityArgument.getPlayers(context, "targets"))
                    }
                )
            )
    }
    
    private fun changeGameMode(context: net.minecraft.commands.CommandContext<CommandSourceStack>, gameType: GameType): Int {
        val player = context.source.player
        if (player != null) {
            player.setGameMode(gameType)
            context.source.sendSystemMessage(Component.literal("Changed game mode to ${gameType.name.lowercase()}"))
            1
        } else {
            context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
            0
        }
    }
    
    private fun changeGameMode(context: net.minecraft.commands.CommandContext<CommandSourceStack>, gameType: GameType, targets: Collection<Player>): Int {
        targets.forEach { player ->
            player.setGameMode(gameType)
        }
        context.source.sendSystemMessage(Component.literal("Changed game mode to ${gameType.name.lowercase()} for ${targets.size} players"))
        targets.size
    }
}
