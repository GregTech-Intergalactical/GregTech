package muramasa.gti.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.CALCITE2X;
import static muramasa.antimatter.material.MaterialTag.CALCITE3X;
import static muramasa.gti.data.TierMaps.INT_CIRCUITS;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.BASIC_BLASTING;
import static muramasa.gti.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        CRUSHED.all().forEach(m -> {
            boolean needsBF = m.needsBlastFurnace() || m.getDirectSmeltInto().needsBlastFurnace();
            if (!m.has(ORE) || !m.has(INGOT)) return;
            Item crushed = CRUSHED.get(m);
            Item dust = DUST.get(m);
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient();
            ItemStack ingot = m.hasDirectSmeltInto() ? INGOT.get(m.getDirectSmeltInto(), 1) : INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == m.getSmeltInto() ? ingot : INGOT.get(m.getSmeltInto(), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 6, 1) * m.getBlastTemp();

                ItemStack blastOut = m.getBlastTemp() > 1750 && m.getSmeltInto().has(INGOT_HOT) ? INGOT_HOT.get(m.getSmeltInto(), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(RecipeIngredient.of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(RecipeIngredient.of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration/4, 120, m.getBlastTemp());
                BLASTING.RB().ii(CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(CRUSHED_CENTRIFUGED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
            }

            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3 * m.getSmeltingMulti(), ingot);
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST.getIngredient(Quicklime, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * mixedOreYield * m.getSmeltingMulti(), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST_TINY.getIngredient(Quicklime, multiplier * 3)).io(Utils.mul(multiplier * 3 * m.getSmeltingMulti(), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            }
        });

        /* PRIMITIVE */
        BASIC_BLASTING.RB().ii(INGOT.getMaterialIngredient(Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).chances(100,50).add(1200, 0);
        BASIC_BLASTING.RB().ii(INGOT.getMaterialIngredient(Copper,2)).io(INGOT.get(AnnealedCopper, 1)).add(1000, 0);

        /* TITANIUM */
        BLASTING.RB().temperature(1700).ii(RecipeIngredient.of(DUST.get(Magnesium,2)), INT_CIRCUITS.get(1))
                .fi(Titaniumtetrachloride.getLiquid(1000))
                .io(INGOT_HOT.get(Titanium,1), DUST.get(MagnesiumChloride,2))
                .add(40*20, 480);
    }
}
