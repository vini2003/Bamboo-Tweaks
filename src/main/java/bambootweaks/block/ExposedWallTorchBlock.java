package bambootweaks.block;

import net.minecraft.block.WallTorchBlock;

public class ExposedWallTorchBlock extends WallTorchBlock {
	public ExposedWallTorchBlock(Settings settings) {
		super(settings);
	}

	@Override
	public String getTranslationKey() {
		return "block.bambootweaks.bamboo_torch";
	}
}