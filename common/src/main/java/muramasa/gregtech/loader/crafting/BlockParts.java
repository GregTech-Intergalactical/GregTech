package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.PipeType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockColoredWall;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.*;

public class BlockParts {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterMaterialTypes.FRAME.all().forEach(frame -> {
            if (!frame.has(AntimatterMaterialTypes.ROD)) return;
            provider.addStackRecipe(output, GTIRef.ID, "", "gtblockparts", AntimatterMaterialTypes.FRAME.get().get(frame).asStack(2),
                    of('R', AntimatterMaterialTypes.ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        AntimatterAPI.all(BlockColoredWall.class, b -> {
            if (b.getMaterial() == Wood){
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(Lead), 'H', HAMMER.getTag(), 'S', SAW.getTag(), 'W', ItemTags.PLANKS), "W W", "SPH", "W W");
            } else {
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(b.getMaterial()), 'H', HAMMER.getTag(), 'W', WRENCH.getTag()), "WPP", "HPP");
            }
        });

        addBrickedCasing(output, provider, Bronze, GregTechData.CASING_BRICKED_BRONZE);
        provider.addStackRecipe(output, GTIRef.ID, "firebricks", "blocks",
                new ItemStack(CASING_FIRE_BRICK), of('F', GTCoreItems.FireBrick), "FF", "FF");
        addBrickedCasing(output, provider, Steel, GregTechData.CASING_BRICKED_STEEL);

        addFirebox(output, provider, Bronze, GregTechData.CASING_FIREBOX_BRONZE);
        addFirebox(output, provider, Steel, GregTechData.CASING_FIREBOX_STEEL);
        addFirebox(output, provider, Titanium, GregTechData.CASING_FIREBOX_TITANIUM);
        addFirebox(output, provider, TungstenSteel, GregTechData.CASING_FIREBOX_TUNGSTENSTEEL);

        addGearbox(output, provider, Bronze, GregTechData.CASING_GEARBOX_BRONZE);
        addGearbox(output, provider, Steel, GregTechData.CASING_GEARBOX_STEEL);
        addGearbox(output, provider, Titanium, GregTechData.CASING_GEARBOX_TITANIUM);
        addGearbox(output, provider, TungstenSteel, GregTechData.CASING_GEARBOX_TUNGSTENSTEEL);

        addPipeCasing(output, provider, Bronze, FLUID_PIPE_BRONZE, GregTechData.CASING_PIPE_BRONZE);
        addPipeCasing(output, provider, Steel, FLUID_PIPE_STEEL, GregTechData.CASING_PIPE_STEEL);
        addPipeCasing(output, provider, Titanium, FLUID_PIPE_TITANIUM, GregTechData.CASING_PIPE_TITANIUM);
        addPipeCasing(output, provider, TungstenSteel, FLUID_PIPE_TUNGSTEN_STEEL, GregTechData.CASING_PIPE_TUNGSTENSTEEL);

        addTurbine(output, provider, Steel, GregTechData.CASING_TURBINE_STEEL);
        addTurbine(output, provider, StainlessSteel, GregTechData.CASING_TURBINE_STAINLESS);
        addTurbine(output, provider, Titanium, GregTechData.CASING_TURBINE_TITANIUM);
        addTurbine(output, provider, TungstenSteel, GregTechData.CASING_TURBINE_TUNGSTENSTEEL);

        addCasing(output, provider, Invar, GregTechData.CASING_HEAT_PROOF);
        addCasing(output, provider, Aluminium, GregTechData.CASING_FROST_PROOF);
        addCasing(output, provider, Steel, GregTechData.CASING_SOLID_STEEL);
        addCasing(output, provider, StainlessSteel, GregTechData.CASING_STAINLESS_STEEL);
        addCasing(output, provider, Titanium, GregTechData.CASING_TITANIUM);
        addCasing(output, provider, Lead, GregTechData.CASING_RADIATION_PROOF);
        addCasing(output, provider, TungstenSteel, GregTechData.CASING_TUNGSTENSTEEL);
        addCasing(output, provider, Tungsten, GregTechData.CASING_TUNGSTEN);
        addCasing(output, provider, Platinum, CASING_PLATINUM);

        addCoil(output, provider, WIRE_CUPRONICKEL.getBlockItem(PipeSize.TINY), COIL_CUPRONICKEL);
        addCoil(output, provider, WIRE_KANTHAL.getBlockItem(PipeSize.TINY), COIL_KANTHAL);
        addCoil(output, provider, WIRE_NICHROME.getBlockItem(PipeSize.TINY), COIL_NICHROME);
        addCoil(output, provider, WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.TINY), COIL_TUNGSTENSTEEL);
        addCoil(output, provider, WIRE_HSSG.getBlockItem(PipeSize.TINY), COIL_HSSG);
        addCoil(output, provider, WIRE_NAQUADAH.getBlockItem(PipeSize.TINY), COIL_NAQUADAH);
        addCoil(output, provider, WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.TINY), COIL_NAQUADAH_ALLOY);
        addCoil(output, provider, WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.TINY), COIL_SUPERCONDUCTOR);

        addTierCasing(output, provider, Tier.ULV);
        addTierCasing(output, provider, LV);
        addTierCasing(output, provider, MV);
        addTierCasing(output, provider, Tier.HV);
        addTierCasing(output, provider, Tier.EV);
        addTierCasing(output, provider, Tier.IV);
        addTierCasing(output, provider, Tier.LUV);
        addTierCasing(output, provider, Tier.ZPM);
        addTierCasing(output, provider, Tier.UV);
        addTierCasing(output, provider, Tier.UHV);

        addTierHull(output, provider, Wood,Tier.ULV);
        addTierHull(output, provider, WroughtIron, LV);
        addTierHull(output, provider, WroughtIron, MV);
        addTierHull(output, provider, Plastic,Tier.HV);
        addTierHull(output, provider, Plastic,Tier.EV);
        addTierHull(output, provider, Plastic,Tier.IV);
        addTierHull(output, provider, Plastic,Tier.LUV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.ZPM);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.UV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.UHV);

        provider.addStackRecipe(output, GTIRef.ID, "", "gtblockparts", new ItemStack(CASING_ENGINE_INTAKE, 2),
                of('P', ROTOR.getMaterialTag(Titanium), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', CASING_TITANIUM, 'G', FLUID_PIPE_TITANIUM.getBlockItem(PipeSize.NORMAL))
                ,
                "PHP", "GFG", "PWP");

        provider.addStackRecipe(output, GTIRef.ID, "", "gtblockparts", new ItemStack(CASING_BRONZE_PLATED_BRICK, 2),
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Bronze))
                        .put('B', Blocks.BRICKS)
                        .put('W', WRENCH.getTag()).put('H', HAMMER.getTag()).build(), "PHP", "PBP", "PWP");

        provider.addItemRecipe(output, "gtblockparts", CASING_STEEL,
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Steel))
                        .put('W', HAMMER.getTag()).build(), "PPP", "PWP", "PPP");
        provider.addStackRecipe(output, GTIRef.ID, "", "gtblockparts", new ItemStack(CASING_FUSION, 1),
                of('P', PLATE.getMaterialTag(TungstenSteel), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', CASING_LUV)
                ,
                "PHP", "PFP", "PWP");
        /*provider.addStackRecipe(output, GTIRef.ID, "", "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CASING_FUSION_2, 1),
                of('P', PLATE.getMaterialTag(Americium), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', CASING_FUSION_1)
                ,
                "PHP", "PFP", "PWP");*/

        //TODO make these also use annealed copper
        provider.addItemRecipe(output, "long_distance_cables", LONG_DIST_WIRE_EV,
                of('C', WIRE_TIN.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", LONG_DIST_WIRE_IV,
                of('C', WIRE_GETTER.apply(PipeSize.HUGE, LV), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", LONG_DIST_WIRE_LUV,
                of('C', WIRE_ELECTRUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", LONG_DIST_WIRE_ZPM,
                of('C', WIRE_ALUMINIUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", LONG_DIST_WIRE_UV,
                of('C', WIRE_PLATINUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_pipes", LONG_DIST_ITEM_PIPE,
                of('E', ITEM_PIPE_ELECTRUM.getBlockItem(PipeSize.NORMAL), 'W', WRENCH.getTag(), 'P', PLATE.getMaterialTag(Plastic)), "PEP", "EWE", "PEP");
        provider.addItemRecipe(output, "long_distance_pipes", LONG_DIST_FLUID_PIPE,
                of('E', FLUID_PIPE_STAINLESS_STEEL.getBlockItem(PipeSize.NORMAL), 'W', WRENCH.getTag(), 'P', PLATE.getMaterialTag(Plastic)), "PEP", "EWE", "PEP");

        addSlabRecipe(output, provider, WHITE_ASPHALT, WHITE_ASPHALT_SLAB);
        addSlabRecipe(output, provider, ORANGE_ASPHALT, ORANGE_ASPHALT_SLAB);
        addSlabRecipe(output, provider, MAGENTA_ASPHALT, MAGENTA_ASPHALT_SLAB);
        addSlabRecipe(output, provider, LIGHT_BLUE_ASPHALT, LIGHT_BLUE_ASPHALT_SLAB);
        addSlabRecipe(output, provider, YELLOW_ASPHALT, YELLOW_ASPHALT_SLAB);
        addSlabRecipe(output, provider, LIME_ASPHALT, LIME_ASPHALT_SLAB);
        addSlabRecipe(output, provider, PINK_ASPHALT, PINK_ASPHALT_SLAB);
        addSlabRecipe(output, provider, GRAY_ASPHALT, GRAY_ASPHALT_SLAB);
        addSlabRecipe(output, provider, LIGHT_GRAY_ASPHALT, LIGHT_GRAY_ASPHALT_SLAB);
        addSlabRecipe(output, provider, CYAN_ASPHALT, CYAN_ASPHALT_SLAB);
        addSlabRecipe(output, provider, PURPLE_ASPHALT, PURPLE_ASPHALT_SLAB);
        addSlabRecipe(output, provider, BLUE_ASPHALT, BLUE_ASPHALT_SLAB);
        addSlabRecipe(output, provider, BROWN_ASPHALT, BROWN_ASPHALT_SLAB);
        addSlabRecipe(output, provider, GREEN_ASPHALT, GREEN_ASPHALT_SLAB);
        addSlabRecipe(output, provider, RED_ASPHALT, RED_ASPHALT_SLAB);
        addSlabRecipe(output, provider, BLACK_ASPHALT, BLACK_ASPHALT_SLAB);

        /*provider.addStackRecipe(output, GTIRef.ID, "blastbrickcasing", "gtblockparts", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(CASING_BLAST_BRICK,4),
                of('C', CASING_FIRE_BRICK,
                'P', PLATE.get(AntimatterMaterials.Iron),
                        'B', AntimatterAPI.get(Item.class, "liquid_creosote_bucket", Ref.SHARED_ID)
        ), "PCP", "CBC", "PCP");*/
    }

    private static void addSlabRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Block full, Block slab){
        provider.addStackRecipe(output, "slabs", new ItemStack(slab, 6), ImmutableMap.of('F', full), "FFF");
        provider.addItemRecipe(output, "slabs", full, ImmutableMap.of('S', slab), "S", "S");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(full), slab, 2).group("slabs").unlockedBy("has_full", provider.hasSafeItem(full)).save(output, new ResourceLocation(GTIRef.ID, "stonecutting/" + AntimatterPlatformUtils.getIdFromItem(slab.asItem()).getPath()));
    }

    private static void addCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GTIRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                ,
                "PHP", "PFP", "PWP");
    }

    private static void addBrickedCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", casing,
                of('B', Items.BRICK, 'P', PLATE.getMaterialTag(mat), 'H', AntimatterDefaultTools.HAMMER.getTag())
                , "PPP", "PHP", "BBB");
    }

    private static void addFirebox(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GTIRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', ROD.getMaterialTag(mat), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "RFR", "PRP");
    }

    private static void addGearbox(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GTIRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem(), 'G', GEAR.getMaterialTag(mat))
                ,
                "PHP", "GFG", "PWP");
    }

    private static void addPipeCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, PipeType<?> pipe, Block casing) {
        provider.addItemRecipe(output, GTIRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', pipe.getBlockItem(PipeSize.NORMAL), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "RFR", "PRP");
    }

    private static void addTurbine(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', AntimatterMaterialTypes.ROD.getMaterialTag(mat), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "PFP", "PRP");
    }

    private static void addCoil(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, PipeItemBlock wire, BlockCoil coil) {
        provider.addItemRecipe(output, "casings", coil,
                of('P', wire, 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }

    private static void addTierCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Tier tier) {
        provider.addItemRecipe(output, "casings", AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID),
                of('P', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }

    private static void addTierHull(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Tier tier) {
        provider.addItemRecipe(output, "hulls", AntimatterAPI.get(BlockCasing.class, "hull_" + tier.getId(), GTIRef.ID),
                of('P', PLATE.getMaterialTag(mat), 'R', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 'K', AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID))
                , "PRP", "WKW");
    }
}
