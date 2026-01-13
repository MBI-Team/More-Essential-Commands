# More Essential Commands

A lightweight, high-performance, cross-platform Minecraft command enhancement mod, providing simple, safe, and easy-to-use commands for players and server administrators.

## Features

### Commands (v1.0)

| Command | Permission Level | Platform | Description |
|---------|------------------|----------|-------------|
| `/fly [on|off]` | OP 2 | Both | Toggle fly mode (no cheats required) |
| `/heal [targets]` | OP 2 | Both | Heal players to full health and hunger |
| `/spawn [targets]` | All Players | Both | Teleport to world spawn |
| `/rtp` | All Players | Both | Random teleport to safe location (Y > 64, not lava/void) |
| `/tpa <player>` | All Players | Both | Request teleport to a player |
| `/tpaccept` | All Players | Both | Accept latest TPA request |
| `/tpdeny` | All Players | Both | Deny latest TPA request |
| `/invsee <player>` | OP 2 | Both | View others' inventory (read-only) |
| `/enderchest <player>` | OP 2 | Both | View others' ender chest (read-only) |
| `/gm <mode>` | OP 2 | Both | Quick game mode switch (s/c/a/sp) |
| `/back` | All Players | Both | Return to previous position (before death/TP) |
| `/ping` | All Players | Both | Show current latency (ms) |

### Permission Levels
- **OP 2**: Default administrator permission (single-player OP is sufficient)
- **All Players**: No OP required, safe for personal use

## Supported Platforms
- **Fabric** (Minecraft 1.21.1+)
- **NeoForge** (Minecraft 1.21.1+)

## Installation

### Fabric
1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) for 1.21.1
3. Download and place the More-Essential-Commands Fabric jar in your `mods` folder

### NeoForge
1. Install [NeoForge](https://neoforged.net/) for Minecraft 1.21.1
2. Download and place the More-Essential-Commands NeoForge jar in your `mods` folder

## Building from Source

### Prerequisites
- Java 17 or higher
- Gradle 8.8 or higher

### Build Instructions
```bash
# Clone the repository
git clone https://github.com/mbi-team/More-Essential-Commands.git
cd More-Essential-Commands

# Build for all platforms
./gradlew build

# Build for specific platforms
./gradlew :fabric:build
./gradlew :neoforge:build
```

The built jars will be located in:
- Fabric: `fabric/build/libs/`
- NeoForge: `neoforge/build/libs/`

## Project Structure

```
More-Essential-Commands/
├── common/              # Shared core code
├── fabric/              # Fabric-specific code
├── neoforge/            # NeoForge-specific code
├── build.gradle.kts     # Main build script
├── settings.gradle.kts  # Project settings
└── gradle.properties    # Project properties
```

## Contributing

Contributions are welcome! Please feel free to submit issues, feature requests, or pull requests.

## License

This project is licensed under the GNU Lesser General Public License v3.0 or later - see the [LICENSE](LICENSE) file for details.

## Credits

- **MBI Team** - Main development

## Contact

- GitHub: [https://github.com/mbi-team/More-Essential-Commands](https://github.com/mbi-team/More-Essential-Commands)
