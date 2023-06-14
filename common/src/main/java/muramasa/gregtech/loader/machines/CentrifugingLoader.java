package muramasa.gregtech.loader.machines;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.gregtech.data.GregTechMaterialTags.CENT;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CENTRIFUGING;

public class CentrifugingLoader {
    public static void init() {
        DUST_IMPURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(0), 1)).add("dust_impure_" + dust.getId(), 400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add("dust_impure_" + dust.getId(),dust.getMass(), 2);
        });
        DUST_PURE.all().forEach(dust -> {
            if (dust.hasByProducts())  {
                int index = dust.getByProducts().size() > 1 ? 1 : 0;
                CENTRIFUGING.RB().ii(of(DUST_PURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(index), 1)).add("dust_pure_" + dust.getId(),dust.getMass(), 2);
            }
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add("dust_pure_" + dust.getId(),dust.getMass(), 2);
        });
        DUST.all().stream().filter(t -> t.has(CENT)).forEach(t -> {
            FluidStack[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(LIQUID)) && !mat.m.has(DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidStack[]::new);
            if (fluids.length > 6) return;
            for (FluidStack fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(DUST)).map(mat -> DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            RecipeIngredient input = DUST.getMaterialIngredient(t, t.getProcessInto().stream().mapToInt(mat -> mat.s).sum());
            CENTRIFUGING.RB().ii(input).io(items).fo(fluids).add("dust_" + t.getId(),t.getMass()*10, t.getMass() < 10 ? 30 : 64);
        });
        //some stone dust recipe from gtnh without metal mixture
        //CENTRIFUGING.RB().ii(of(DUST.get(Stone, 32))).io(DUST.get(Quartz, 9), DUST.get(PotassiumFeldspar, 9), DUST.get(Marble, 8), DUST.get(Biotite, 4),
        //        DUST.get(Sodalite, 4)).add("stone_dust",7680, 30);

        //Cake Centrifuging
        CENTRIFUGING.RB().ii(of(DUST.get(ThoriumCake, 5))).io(DUST.get(ThoriumDioxide, 1), DUST.get(TrithoriumOctoxide, 4)).add("thorium_cake_centrifuging",400, 500);
        CENTRIFUGING.RB().ii(of(DUST.get(UraniumCake, 5))).io(DUST.get(UraniumDioxide, 1), DUST.get(TriuraniumOctoxide, 4)).add("uranium_cake_centrifuging",400, 500);
        CENTRIFUGING.RB().ii(of(GTRubberData.RUBBER_LOGS)).io(new ItemStack(GTRubberData.StickyResin), new ItemStack(GregTechData.PlantBall), DUST.get(Carbon, 1), DUST.get(Wood, 1)).fo(Methane.getGas(60)).chances(0.5, 0.375, 0.25, 0.25).add("rubber_logs", 200, 20);

    }
}

