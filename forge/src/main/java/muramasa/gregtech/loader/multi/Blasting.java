package muramasa.gregtech.loader.multi;

import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.Ref;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTags.CALCITE2X;
import static muramasa.antimatter.material.MaterialTags.CALCITE3X;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.BASIC_BLASTING;
import static muramasa.gregtech.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        CRUSHED.all().forEach(m -> {
            boolean needsBF = m.has(MaterialTags.NEEDS_BLAST_FURNACE) || MaterialTags.DIRECT_SMELT_INTO.getMapping(m).has(MaterialTags.NEEDS_BLAST_FURNACE);
            if (!m.has(ORE) || !m.has(INGOT)) return;
            Item crushed = CRUSHED.get(m);
            Item dust = DUST.get(m);
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient();
            ItemStack ingot = m != MaterialTags.DIRECT_SMELT_INTO.getMapping(m) ? INGOT.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == MaterialTags.SMELT_INTO.getMapping(m) ? ingot : INGOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 6, 1) * MaterialTags.BLAST_FURNACE_TEMP.getInt(m);

                ItemStack blastOut = MaterialTags.BLAST_FURNACE_TEMP.getInt(m) > 1750 && MaterialTags.SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(RecipeIngredient.of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(RecipeIngredient.of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration/4, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_CENTRIFUGED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
            }

            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot);
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST.getIngredient(Quicklime, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * mixedOreYield * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST_TINY.getIngredient(Quicklime, multiplier * 3)).io(Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            }
        });

        /* PRIMITIVE */
        BASIC_BLASTING.RB().ii(INGOT.getMaterialIngredient(Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).chances(100,50).add(1200, 0);

        /* TITANIUM */
        BLASTING.RB().temperature(1700).ii(RecipeIngredient.of(DUST.get(Magnesium,2)), INT_CIRCUITS.get(1))
                .fi(Titaniumtetrachloride.getLiquid(1000))
                .io(INGOT_HOT.get(Titanium,1), DUST.get(MagnesiumChloride,2))
                .add(40*20, 480);
    }
}
