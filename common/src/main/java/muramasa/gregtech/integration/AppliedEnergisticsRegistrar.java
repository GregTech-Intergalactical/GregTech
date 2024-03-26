package muramasa.gregtech.integration;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.data.TierMaps;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.E_BLAST_FURNACE;
import static muramasa.gregtech.data.RecipeMaps.LASER_ENGRAVER;

public class AppliedEnergisticsRegistrar implements IAntimatterRegistrar {

    public AppliedEnergisticsRegistrar(){
        onRegistrarInit();
    }

    @Override
    public String getId() {
        return Ref.MOD_AE;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT){
            GEM.replacement(CertusQuartz, () -> getAe2Item("certus_quartz_crystal"));
            GEM.replacement(ChargedCertusQuartz, () -> getAe2Item("charged_certus_quartz_crystal"));
            GEM.replacement(Fluix, () -> getAe2Item("fluix_crystal"));
            DUST.replacement(CertusQuartz, () -> getAe2Item("certus_quartz_dust"));
            DUST.replacement(Fluix, () -> getAe2Item("fluix_dust"));
            BLOCK.replacement(CertusQuartz, () -> getAe2Item("quartz_block"));
            BLOCK.replacement(Fluix, () -> getAe2Item("fluix_block"));
            ORE.replacement(CertusQuartz, AntimatterStoneTypes.STONE, () -> getAe2Item("quartz_ore"));
            ORE.replacement(CertusQuartz, AntimatterStoneTypes.DEEPSLATE, () -> getAe2Item("deepslate_quartz_ore"));
        }
    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded(getId());
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
    }

    public static void machineRecipes(){
        RecipeMaps.FORMING_PRESS.RB().ii(GEM.getMaterialIngredient(CertusQuartz, 1), of(getAe2Item("calculation_processor_press"), 1).setNoConsume()).io(new ItemStack(getAe2Item("printed_calculation_processor"))).add("printed_calculation_processor", 200, 16);
        RecipeMaps.FORMING_PRESS.RB().ii(GEM.getMaterialIngredient(Diamond, 1), of(getAe2Item("engineering_processor_press"), 1).setNoConsume()).io(new ItemStack(getAe2Item("printed_engineering_processor"))).add("printed_engineering_processor", 200, 16);
        RecipeMaps.FORMING_PRESS.RB().ii(PLATE.getMaterialIngredient(Gold, 1), of(getAe2Item("logic_processor_press"), 1).setNoConsume()).io(new ItemStack(getAe2Item("printed_logic_processor"))).add("printed_logic_processor", 200, 16);
        RecipeMaps.FORMING_PRESS.RB().ii(of(TagUtils.getForgelikeItemTag("silicon")), of(getAe2Item("silicon_press"), 1).setNoConsume()).io(new ItemStack(getAe2Item("printed_silicon"))).add("printed_silicon", 200, 16);
        RecipeMaps.CENTRIFUGE.RB().ii(of(getAe2Item("sky_dust")))
                .io(/*DUST_SMALL.get(BasalticMineralSand, 1), */DUST_SMALL.get(Olivine, 1), DUST_SMALL.get(Obsidian, 1), DUST_SMALL.get(Basalt, 1), DUST_SMALL.get(Flint, 1),DUST_SMALL.get(RareEarth, 1))
                .chances(0.2,0.2,0.2,0.2,0.2)
                .add("sky_dust", 64, 20);
        RecipeMaps.AUTOCLAVE.RB().ii(of(getAe2Item("certus_crystal_seed"), 1).setIgnoreNbt()).fi(Water.getLiquid(200)).io(GEM.get(CertusQuartz, 2)).add("certus_quartz_from_seed", 2000, 24);
        RecipeMaps.AUTOCLAVE.RB().ii(of(getAe2Item("fluix_crystal_seed"), 1).setIgnoreNbt()).fi(Water.getLiquid(200)).io(GEM.get(Fluix, 2)).add("fluix_from_seed", 2000, 24);
        RecipeMaps.AUTOCLAVE.RB().ii(of(getAe2Item("certus_crystal_seed"), 1).setIgnoreNbt()).fi(DistilledWater.getLiquid(200)).io(GEM.get(CertusQuartz, 2)).add("certus_quartz_from_seed_2", 1000, 24);
        RecipeMaps.AUTOCLAVE.RB().ii(of(getAe2Item("fluix_crystal_seed"), 1).setIgnoreNbt()).fi(DistilledWater.getLiquid(200)).io(GEM.get(Fluix, 1)).add("fluix_from_seed_2", 1000, 24);
        RecipeMaps.MIXER.RB().ii(GEM.getIngredient(ChargedCertusQuartz, 1), DUST.getMaterialIngredient(Redstone, 1), of(ForgeCTags.GEMS_QUARTZ_ALL)).fi(Water.getLiquid(500)).io(DUST.get(Fluix, 2)).add("fluix_crystal", 20, 16);
        RecipeMaps.MIXER.RB().ii(GEM.getIngredient(ChargedCertusQuartz, 1), DUST.getMaterialIngredient(Redstone, 1), of(ForgeCTags.GEMS_QUARTZ_ALL)).fi(DistilledWater.getLiquid(500)).io(DUST.get(Fluix, 2)).add("fluix_crystal_2", 20, 16);
        RecipeMaps.ASSEMBLER.RB().ii(of(getAe2Item("printed_logic_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("logic_processor"))).add("logic_processor", 64, 32);
        RecipeMaps.ASSEMBLER.RB().ii(of(getAe2Item("printed_engineering_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("engineering_processor"))).add("engineering_processor", 64, 32);
        RecipeMaps.ASSEMBLER.RB().ii(of(getAe2Item("printed_calculation_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("calculation_processor"))).add("calculation_processor", 64, 32);
        RecipeMaps.ASSEMBLER.RB().ii(DUST.getMaterialIngredient(CertusQuartz, 1), of(ItemTags.SAND)).io(new ItemStack(getAe2Item("certus_crystal_seed"), 2)).add("certus_crystal_seed", 64, 8);
        RecipeMaps.ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Fluix, 1), of(ItemTags.SAND)).io(new ItemStack(getAe2Item("fluix_crystal_seed"), 2)).add("fluix_crystal_seed", 64, 8);
        RecipeMaps.MACERATOR.RB().ii(of(getAe2Item("sky_stone_chest"))).io(new ItemStack(getAe2Item("sky_dust"), 8)).add("sky_dust_from_chest", 400, 2);
        RecipeMaps.MACERATOR.RB().ii(of(getAe2Item("sky_stone_block"))).io(new ItemStack(getAe2Item("sky_dust"))).add("sky_dust", 400, 2);
        RecipeMaps.ELECTROLYZER.RB().ii(GEM.getMaterialIngredient(CertusQuartz, 1)).io(new ItemStack(getAe2Item("charged_certus_quartz_crystal"))).add("charged_certus_quartz", 2000, 30);
        E_BLAST_FURNACE.RB().ii(RecipeIngredient.of(TagUtils.getForgelikeItemTag("silicon")), TierMaps.INT_CIRCUITS.get(1)).io(INGOT.get(Silicon)).add("silicon_ingot_from_silicon", 1683, 120, 1683);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(GreenSapphire, 1).setNoConsume()).io(new ItemStack(getAe2Item("logic_processor_press"))).add("inscriber_logic_press", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(Opal, 1).setNoConsume()).io(new ItemStack(getAe2Item("calculation_processor_press"))).add("inscriber_calculation_press", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(BlueTopaz, 1).setNoConsume()).io(new ItemStack(getAe2Item("calculation_processor_press"))).add("inscriber_calculation_press_2", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(Sapphire, 1).setNoConsume()).io(new ItemStack(getAe2Item("calculation_processor_press"))).add("inscriber_calculation_press_3", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(YellowGarnet, 1).setNoConsume()).io(new ItemStack(getAe2Item("engineering_processor_press"))).add("inscriber_engineering_press", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(Dilithium, 1).setNoConsume()).io(new ItemStack(getAe2Item("silicon_press"))).add("inscriber_silicon_press", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(Diamond, 1).setNoConsume()).io(new ItemStack(getAe2Item("silicon_press"))).add("inscriber_silicon_press_2", 2000, 1920);
        LASER_ENGRAVER.RB().ii(BLOCK.getMaterialIngredient(Iron, 1), LENS.getMaterialIngredient(Glass, 1).setNoConsume()).io(new ItemStack(getAe2Item("silicon_press"))).add("inscriber_silicon_press_3", 2000, 1920);
    }
    
    public static void craftingRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        SimpleCookingRecipeBuilder.smelting(DUST.getMaterialIngredient(Silicon, 1), getAe2Item("silicon"), 0.5F, 200).unlockedBy("has_silicon_dust", provider.hasSafeItem(DUST.getMaterialTag(Silicon))).save(output, GTIRef.ID + ":silicon");
        SimpleCookingRecipeBuilder.blasting(DUST.getMaterialIngredient(Silicon, 1), getAe2Item("silicon"), 0.5F, 200).unlockedBy("has_silicon_dust", provider.hasSafeItem(DUST.getMaterialTag(Silicon))).save(output, GTIRef.ID + ":silicon_blasting");
    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        event.setMaterial(MilkyQuartz).addByProduct(CertusQuartz, Barite);
        event.setMaterial(CertusQuartz).addByProduct(MilkyQuartz, Barite);
    }

    public static Item getAe2Item(String id){
        return AntimatterPlatformUtils.getItemFromID(Ref.MOD_AE, id);
    }

    public static Block getAe2Block(String id){
        return AntimatterPlatformUtils.getBlockFromId(Ref.MOD_AE, id);
    }
}
