package com.github.mbi_team.moreessentialcommands.neoforge

import com.github.mbi_team.moreessentialcommands.MoreEssentialCommands
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.RegisterCommandsEvent

@Mod(MoreEssentialCommands.MOD_ID)
class MoreEssentialCommandsNeoForge {
    init {
        // Initialize common module
        MoreEssentialCommands.init()
        
        // Register event listeners
        val bus: IEventBus = net.neoforged.fml.ModLoadingContext.get().modEventBus
        bus.addListener(this::registerCommands)
    }
    
    private fun registerCommands(event: RegisterCommandsEvent) {
        MoreEssentialCommands.registerCommands(event.dispatcher)
    }
}
