package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
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
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            boolean needsBF = m.has(MaterialTags.NEEDS_BLAST_FURNACE) || MaterialTags.DIRECT_SMELT_INTO.getMapping(m).has(MaterialTags.NEEDS_BLAST_FURNACE);
            if (!m.has(AntimatterMaterialTypes.ORE) || !m.has(AntimatterMaterialTypes.INGOT)) return;
            Item crushed = AntimatterMaterialTypes.CRUSHED.get(m);
            Item dust = AntimatterMaterialTypes.DUST.get(m);
            RecipeIngredient ore = AntimatterMaterialTypes.ORE.get().get(m, STONE).asIngredient();
            ItemStack ingot = m != MaterialTags.DIRECT_SMELT_INTO.getMapping(m) ? AntimatterMaterialTypes.INGOT.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 1) : AntimatterMaterialTypes.INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == MaterialTags.SMELT_INTO.getMapping(m) ? ingot : AntimatterMaterialTypes.INGOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 6, 1) * MaterialTags.BLAST_FURNACE_TEMP.getInt(m);

                ItemStack blastOut = MaterialTags.BLAST_FURNACE_TEMP.getInt(m) > 1750 && MaterialTags.SMELT_INTO.getMapping(m).has(AntimatterMaterialTypes.INGOT_HOT) ? AntimatterMaterialTypes.INGOT_HOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(RecipeIngredient.of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(RecipeIngredient.of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration/4, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(AntimatterMaterialTypes.CRUSHED_CENTRIFUGED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(AntimatterMaterialTypes.DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(AntimatterMaterialTypes.DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
            }

            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot);
                ItemStack darkAsh = AntimatterMaterialTypes.DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, AntimatterMaterialTypes.DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, AntimatterMaterialTypes.DUST.getIngredient(Quicklime, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = AntimatterMaterialTypes.DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, AntimatterMaterialTypes.DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * mixedOreYield * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, AntimatterMaterialTypes.DUST_TINY.getIngredient(Quicklime, multiplier * 3)).io(Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            }
        });

        /* PRIMITIVE */
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron,1)).io(AntimatterMaterialTypes.INGOT.get(Steel, 1), AntimatterMaterialTypes.DUST_SMALL.get(DarkAsh,8)).chances(1.0, 0.5).add(1200, 0);

        /* TITANIUM */
        BLASTING.RB().temperature(1700).ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(Magnesium,2)), INT_CIRCUITS.get(1))
                .fi(Titaniumtetrachloride.getLiquid(1000))
                .io(AntimatterMaterialTypes.INGOT_HOT.get(Titanium,1), AntimatterMaterialTypes.DUST.get(MagnesiumChloride,2))
                .add(40*20, 480);
    }
}
