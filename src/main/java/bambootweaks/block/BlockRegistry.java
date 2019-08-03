package bambootweaks.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.block.Material;
import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;

public class BlockRegistry {
    public static final BlockBambooFenceGate BAMBOO_FENCE_GATE_BLOCK = new BlockBambooFenceGate(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());    
    public static final BlockBambooFence BAMBOO_FENCE_BLOCK = new BlockBambooFence(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());    
    public static final BlockBambooLadder BAMBOO_LADDER_BLOCK = new BlockBambooLadder(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());
    public static final BlockBambooSpike BAMBOO_SPIKE_BLOCK = new BlockBambooSpike(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());
    public static final BlockBambooRod BAMBOO_ROD = new BlockBambooRod(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());
    public static final Block BAMBOO_BLOCK = new Block(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0F).build());
    public static final BlockBambooTorch BAMBOO_TORCH_BLOCK = new BlockBambooTorch(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).build());
    public static final BlockBambooTorchWall BAMBOO_TORCH_BLOCK_WALL = new BlockBambooTorchWall(FabricBlockSettings.of(Material.PART).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).build());
    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_fence_gate"), BAMBOO_FENCE_GATE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_fence"), BAMBOO_FENCE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_ladder"), BAMBOO_LADDER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_spike"), BAMBOO_SPIKE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_rod"), BAMBOO_ROD);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_block"), BAMBOO_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch"), BAMBOO_TORCH_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("bambootweaks", "bamboo_torch_wall"), BAMBOO_TORCH_BLOCK_WALL);
    }
}