package bambootweaks.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block BAMBOO_BLOCK = new Block(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.5F, 2.5F).build());
    public static final BlockBambooDoor BAMBOO_DOOR = new BlockBambooDoor(FabricBlockSettings.of(Material.BAMBOO).strength(3.0F, 0.0F).sounds(BlockSoundGroup.BAMBOO).build());
    public static final BlockBambooFence BAMBOO_FENCE_BLOCK = new BlockBambooFence(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build());    
    public static final BlockBambooFenceGate BAMBOO_FENCE_GATE_BLOCK = new BlockBambooFenceGate(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build());    
    public static final BlockBambooFenceWall BAMBOO_FENCE_WALL_BLOCK = new BlockBambooFenceWall(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build());    
    public static final BlockBambooLadder BAMBOO_LADDER_BLOCK = new BlockBambooLadder(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0.0F).build());
    public static final BlockBambooRod BAMBOO_ROD = new BlockBambooRod(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).breakInstantly().build());
    public static final BlockBambooSpike BAMBOO_SPIKE_BLOCK = new BlockBambooSpike(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build());
    public static final BlockBambooStairs BAMBOO_STAIRS_BLOCK = new BlockBambooStairs(BAMBOO_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build());    
    public static final BlockBambooTorch BAMBOO_TORCH_BLOCK = new BlockBambooTorch(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).build());
    public static final BlockBambooTorchWall BAMBOO_TORCH_BLOCK_WALL = new BlockBambooTorchWall(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).build());    

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_block"), BAMBOO_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_door"), BAMBOO_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_fence_gate"), BAMBOO_FENCE_GATE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_fence_wall"), BAMBOO_FENCE_WALL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_fence"), BAMBOO_FENCE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_ladder"), BAMBOO_LADDER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_rod"), BAMBOO_ROD);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_spike"), BAMBOO_SPIKE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_stairs"), BAMBOO_STAIRS_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch_wall"), BAMBOO_TORCH_BLOCK_WALL);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch"), BAMBOO_TORCH_BLOCK);
    }
}