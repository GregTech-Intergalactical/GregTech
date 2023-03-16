package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.BucketItem;
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
import static muramasa.gregtech.data.TierMaps.TIER_CIRCUITS;

public class BlockParts {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterMaterialTypes.FRAME.all().forEach(frame -> {
            if (!frame.has(AntimatterMaterialTypes.ROD)) return;
            provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), AntimatterMaterialTypes.FRAME.get().get(frame).asItem(),
                    of('R', AntimatterMaterialTypes.ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        addBrickedCasing(output, provider, Bronze, GregTechData.CASING_BRICKED_BRONZE);
        addBrickedCasing(output, provider, Fireclay, CASING_FIRE_BRICK);
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

        addTierCasing(output, provider, WroughtIron, Lead, GregTechData.CASING_ULV,1);
        addTierCasing(output, provider, Steel, AntimatterMaterials.Copper, GregTechData.CASING_LV,2);
        addTierCasing(output, provider, Aluminium, StainlessSteel, GregTechData.CASING_MV,3);
        addTierCasing(output, provider, TungstenSteel, TungstenCarbide, GregTechData.CASING_HV,4);
        addTierCasing(output, provider, Ultimet, HSSG, GregTechData.CASING_EV,5);
        addTierCasing(output, provider, HSSE, HSSS, GregTechData.CASING_IV,6);
        //addTierCasing(output, provider, TungstenSteel, GregTechData.CASING_LUV);
        //addTierCasing(output, provider, Osmiridium, GregTechData.CASING_ZPM);
        //addTierCasing(output, provider, Ultimet, GregTechData.CASING_UV);
        //addTierCasing(output, provider, RedSteel, GregTechData.CASING_MAX);

        addTierHull(output, provider, AntimatterMaterials.Iron, WIRE_RED_ALLOY, CircuitBasic, GregTechData.CASING_ULV, GregTechData.HULL_ULV,1);
        addTierHull(output, provider, AnnealedCopper, WIRE_TIN, CircuitBasic, GregTechData.CASING_LV, GregTechData.HULL_LV,2);
        addTierHull(output, provider, Silver, WIRE_CUPRONICKEL, CircuitGood, GregTechData.CASING_MV,GregTechData.HULL_MV,3);
        addTierHull(output, provider, SterlingSilver, WIRE_ELECTRUM, CircuitGood, GregTechData.CASING_HV, GregTechData.HULL_HV,4);
        addTierHull(output, provider, RoseGold, WIRE_NICHROME, CircuitAdv, GregTechData.CASING_EV, GregTechData.HULL_EV,5);
        addTierHull(output, provider, RedSteel, WIRE_NIOBIUM_TITANIUM, CircuitAdv, GregTechData.CASING_IV, GregTechData.HULL_IV,6);

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

        provider.addStackRecipe(output, Ref.ID, "blastbrickcasing", "gtblockparts", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(CASING_BLAST_BRICK,4),
                of('C', CASING_FIRE_BRICK,
                'P', PLATE.get(AntimatterMaterials.Iron),
                        'B', AntimatterAPI.get(Item.class, "liquid_creosote_bucket", muramasa.antimatter.Ref.SHARED_ID)
        ), "PCP", "CBC", "PCP");
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

    private static void addTierCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Material mat2, Block casing, int tier) {
        provider.addItemRecipe(output, "casings", "has_casing", provider.hasSafeItem(WRENCH.getTag()), casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'B', AntimatterMaterialTypes.BOLT.getMaterialTag(mat2))
                , "BPB", "PWP", "BPB");
        }

    private static void addTierHull(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
        provider.addItemRecipe(output, "hulls", "has_hull", provider.hasSafeItem(WRENCH.getTag()), hull,
                of('S', AntimatterMaterialTypes.SCREW.getMaterialTag(mat), 'R', Items.REDSTONE, 'W', w.getBlockItem(PipeSize.VTINY), 'K', casing, 'C', TIER_CIRCUITS.getOrDefault(tier, circ))
                , "SRS", "WKW", "SCS");
        }
}
