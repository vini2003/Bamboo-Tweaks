package bambootweaks.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.util.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.block.Material;
import net.minecraft.block.Block;

public class BlockRegistry {
    public static final Block BambooTorchBlockGroundBlock = new BlockBambooTorchGround(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.WOOD).build());
    public static final Block BambooTorchBlockWallBlock = new BlockBambooTorchWall(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.WOOD).build());
    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch"), BambooTorchBlockGroundBlock);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch_wall"), BambooTorchBlockWallBlock);
    }
}