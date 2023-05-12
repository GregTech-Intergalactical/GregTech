package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.*;

public class BlockParts {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterMaterialTypes.FRAME.all().forEach(frame -> {
            if (!frame.has(AntimatterMaterialTypes.ROD)) return;
            provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), AntimatterMaterialTypes.FRAME.get().get(frame).asItem(),
                    of('R', AntimatterMaterialTypes.ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        addBrickedCasing(output, provider, Bronze, GregTechData.CASING_BRICKED_BRONZE);
        provider.addStackRecipe(output, GTIRef.ID, "firebricks", "blocks", "has_fire_brick", provider.hasSafeItem(FireBrick),
                new ItemStack(CASING_FIRE_BRICK), of('F', FireBrick), "FF", "FF");
        addBrickedCasing(output, provider, Steel, GregTechData.CASING_BRICKED_STEEL);

        addFirebox(output, provider, Bronze, GregTechData.CASING_FIREBOX_BRONZE);
        addFirebox(output, provider, Steel, GregTechData.CASING_FIREBOX_STEEL);
        addFirebox(output, provider, Titanium, GregTechData.CASING_FIREBOX_TITANIUM);
        addFirebox(output, provider, TungstenSteel, GregTechData.CASING_FIREBOX_TUNGSTENSTEEL);

        addTurbine(output, provider, Steel, GregTechData.CASING_TURBINE_1);
        addTurbine(output, provider, StainlessSteel, GregTechData.CASING_TURBINE_2);
        addTurbine(output, provider, Titanium, GregTechData.CASING_TURBINE_3);
        addTurbine(output, provider, TungstenSteel, GregTechData.CASING_TURBINE_4);

        addCasing(output, provider, Invar, GregTechData.CASING_HEAT_PROOF);
        addCasing(output, provider, Aluminium, GregTechData.CASING_FROST_PROOF);
        addCasing(output, provider, Steel, GregTechData.CASING_SOLID_STEEL);
        addCasing(output, provider, StainlessSteel, GregTechData.CASING_STAINLESS_STEEL);
        addCasing(output, provider, Titanium, GregTechData.CASING_TITANIUM);
        addCasing(output, provider, Lead, GregTechData.CASING_RADIATION_PROOF);
        addCasing(output, provider, TungstenSteel, GregTechData.CASING_TUNGSTENSTEEL);

        addTierCasing(output, provider, Tier.ULV);
        addTierCasing(output, provider, Tier.LV);
        addTierCasing(output, provider, Tier.MV);
        addTierCasing(output, provider, Tier.HV);
        addTierCasing(output, provider, Tier.EV);
        addTierCasing(output, provider, Tier.IV);
        addTierCasing(output, provider, Tier.LUV);
        addTierCasing(output, provider, Tier.ZPM);
        addTierCasing(output, provider, Tier.UV);
        addTierCasing(output, provider, Tier.MAX);

        addTierHull(output, provider, AntimatterMaterials.Wood,Tier.ULV);
        addTierHull(output, provider, WroughtIron,Tier.LV);
        addTierHull(output, provider, WroughtIron,Tier.MV);
        addTierHull(output, provider, Polyethylene,Tier.HV);
        addTierHull(output, provider, Polyethylene,Tier.EV);
        addTierHull(output, provider, Polyethylene,Tier.IV);
        addTierHull(output, provider, Polyethylene,Tier.LUV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.ZPM);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.UV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.MAX);

        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CASING_BRONZE_PLATED_BRICK,
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Bronze))
                        .put('B', Blocks.BRICKS)
                        .put('W', WRENCH.getTag()).build(), "BPB", "PWP", "BPB");

        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CASING_STEEL,
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Steel))
                        .put('B', INGOT.get(Steel))
                        .put('W', WRENCH.getTag()).build(), "BPB", "PWP", "BPB");

        /*provider.addStackRecipe(output, GTIRef.ID, "blastbrickcasing", "gtblockparts", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(CASING_BLAST_BRICK,4),
                of('C', CASING_FIRE_BRICK,
                'P', PLATE.get(AntimatterMaterials.Iron),
                        'B', AntimatterAPI.get(Item.class, "liquid_creosote_bucket", Ref.SHARED_ID)
        ), "PCP", "CBC", "PCP");*/
    }

    private static void addCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                ,
                "PHP", "PFP", "PWP");
    }

    private static void addBrickedCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                of('B', Items.BRICK, 'P', PLATE.getMaterialTag(mat), 'H', AntimatterDefaultTools.HAMMER.getTag())
                , "PPP", "PHP", "BBB");
    }

    private static void addFirebox(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PHP", "PFP", "PWP");
    }

    private static void addTurbine(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                of('P', PLATE.getMaterialTag(mat), 'R', AntimatterMaterialTypes.ROD.getMaterialTag(mat), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "PFP", "PRP");
    }

    private static void addTierCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Tier tier) {
        provider.addItemRecipe(output, "casings", "has_casing", provider.hasSafeItem(WRENCH.getTag()), AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID),
                of('P', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }

    private static void addTierHull(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Tier tier) {
        provider.addItemRecipe(output, "hulls", "has_hull", provider.hasSafeItem(WRENCH.getTag()), AntimatterAPI.get(BlockCasing.class, "hull_" + tier.getId(), GTIRef.ID),
                of('P', PLATE.getMaterialTag(mat), 'R', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 'K', AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID))
                , "PRP", "WKW");
    }
}
