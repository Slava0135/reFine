package content;

import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;
import world.blocks.production.Furnace;

import static mindustry.type.ItemStack.with;

public class ReBlocks implements ContentList {

    public static Block furnace, electricFurnace, oreGranite;

    @Override
    public void load() {

        oreGranite = new OreBlock(ReItems.granite){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        furnace = new Furnace("furnace"){{
           requirements(Category.production, with(ReItems.granite, 12));
           size = 2;
        }};
    }
}
