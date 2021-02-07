package content;

import mindustry.ctype.ContentList;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;

public class ReBlocks implements ContentList {

    public static Block furnace, electricFurnace, granite;

    @Override
    public void load() {
        granite = new OreBlock(ReItems.granite){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
    }
}
