package content;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public class ReItems implements ContentList {

    public static Item rawCopper, rawLead, rawTitanium, granite;

    @Override
    public void load() {

        rawCopper = new Item("raw-copper", Color.valueOf("d99d73")){{
            hardness = 1;
            alwaysUnlocked = true;
        }};

        rawLead = new Item("raw-lead", Color.valueOf("8c7fa9")){{
            hardness = 1;
            alwaysUnlocked = true;
        }};

        rawTitanium = new Item("raw-titanium", Color.valueOf("8da1e3")){{
            hardness = 3;
        }};

        granite = new Item("granite", Color.valueOf("8B8265"));
    }
}
