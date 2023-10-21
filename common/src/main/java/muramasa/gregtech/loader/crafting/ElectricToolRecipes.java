package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;

public class ElectricToolRecipes {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        PLATE.all().forEach(m -> {
            if (m.has(DRILLBIT)){
                provider.addItemRecipe(output, "bits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), DRILLBIT.get(m),
                        ImmutableMap.of('P', PLATE.getMaterialTag(m), 'S', PLATE.getMaterialTag(Steel), 'H', HAMMER.getTag()), "PSP", "PSP", "SHS");
            }
            if (m.has(WRENCHBIT)){
                provider.addItemRecipe(output, "bits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), WRENCHBIT.get(m),
                        ImmutableMap.of('P', PLATE.getMaterialTag(m),
                                'R', RING.getMaterialTag(Steel),
                                'S', SCREW.getMaterialTag(Steel),
                                'H', HAMMER.getTag(),
                                's', SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
            }

            if (m.has(CHAINSAWBIT)){
                provider.addItemRecipe(output, "bits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), CHAINSAWBIT.get(m),
                        ImmutableMap.of('P', PLATE.getMaterialTag(m),
                                'R', RING.getMaterialTag(Steel),
                                'S', PLATE.getMaterialTag(Steel),
                                'H', HAMMER.getTag()), "SRS", "PHP", "SRS");
            }

            if (m.has(BUZZSAW_BLADE)){
                provider.addItemRecipe(output, "bits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), BUZZSAW_BLADE.get(m),
                        ImmutableMap.of('P', PLATE.getMaterialTag(m),
                                'W', WRENCH.getTag(),
                                'C', WIRE_CUTTER.getTag(),
                                'H', HAMMER.getTag(),
                                'F', FILE.getTag()), "WPH", "P P", "FPC");
            }

        });
        CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.SCREWDRIVER.getTag());

        IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "drill_lv");
        IAntimatterTool drill_mv = AntimatterAPI.get(IAntimatterTool.class, "drill_mv");
        IAntimatterTool drill_hv = AntimatterAPI.get(IAntimatterTool.class, "drill_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_lv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(DRILLBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Aluminium))
                        .put('G', GEAR_SMALL.getMaterialTag(Aluminium))
                        .put('M', MotorLV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_mv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 200000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(DRILLBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MEDIUM).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(StainlessSteel))
                        .put('G', GEAR_SMALL.getMaterialTag(StainlessSteel))
                        .put('M', MotorMV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_hv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_hv, Material.NULL, Titanium, 0, 800000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(DRILLBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LARGE).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Titanium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Titanium))
                        .put('G', GEAR_SMALL.getMaterialTag(Titanium))
                        .put('M', MotorHV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_lv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_mv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.DRILL.getId() + "_hv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, resolveStack(drill_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv");
        IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv");
        IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_lv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(CHAINSAWBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Aluminium))
                        .put('G', GEAR_SMALL.getMaterialTag(Aluminium))
                        .put('M', MotorLV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_mv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 200000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(CHAINSAWBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MEDIUM).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(StainlessSteel))
                        .put('G', GEAR_SMALL.getMaterialTag(StainlessSteel))
                        .put('M', MotorMV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_hv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 800000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(CHAINSAWBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LARGE).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Titanium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Titanium))
                        .put('G', GEAR_SMALL.getMaterialTag(Titanium))
                        .put('M', MotorHV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");

        IAntimatterTool electric_wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv");
        IAntimatterTool electric_wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv");
        IAntimatterTool electric_wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_lv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(WRENCHBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Aluminium))
                        .put('G', GEAR_SMALL.getMaterialTag(Aluminium))
                        .put('M', MotorLV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_mv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 200000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(WRENCHBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MEDIUM).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(StainlessSteel))
                        .put('G', GEAR_SMALL.getMaterialTag(StainlessSteel))
                        .put('M', MotorMV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_hv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 800000),
                ImmutableMap.<Character, Object>builder()
                        .put('W', PropertyIngredient.of(WRENCHBIT, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LARGE).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Titanium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Titanium))
                        .put('G', GEAR_SMALL.getMaterialTag(Titanium))
                        .put('M', MotorHV).build(), "sWS", "GMG", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_lv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_mv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_hv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv");
        IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv");
        IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-lv"),output, GTIRef.ID, "", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Aluminium))
                        .put('G', GEAR_SMALL.getMaterialTag(Aluminium))
                        .put('M', MotorLV).build(), "PbM", "SBG", "sGP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-mv"),output, GTIRef.ID, "", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 200000),
                ImmutableMap.<Character, Object>builder()
                        .put('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MEDIUM).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(StainlessSteel))
                        .put('G', GEAR_SMALL.getMaterialTag(StainlessSteel))
                        .put('M', MotorMV).build(), "PbM", "SBG", "sGP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-hv"),output, GTIRef.ID, "", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 800000),
                ImmutableMap.<Character, Object>builder()
                        .put('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"))
                        .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LARGE).build())
                        .put('P', PropertyIngredient.builder("secondary").mats(Titanium).types(PLATE).build())
                        .put('s', SCREW.getMaterialTag(Titanium))
                        .put('G', GEAR_SMALL.getMaterialTag(Titanium))
                        .put('M', MotorHV).build(), "PbM", "SBG", "sGP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-lv"),output, GTIRef.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-mv"),output, GTIRef.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-hv"),output, GTIRef.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "PS", "B ");

        IAntimatterTool electric_screwdriver_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv");

        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, GTIRef.ID, "", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('R', PropertyIngredient.builder("primary").types(ROD_LONG).tool(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER, true).build())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build())
                        .put('M', MotorLV)
                        .put('P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build())
                        .put('S', SCREW.getMaterialTag(Aluminium))
                        .put('G', GEAR_SMALL.getMaterialTag(Aluminium))
                        .put('s', SCREWDRIVER.getTag()).build(), "PsR", "MGS", "GbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, GTIRef.ID, AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "_power_unit_lv", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD_LONG).tool(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER, true).build(),'S', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "R ", " S");

        IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.JACKHAMMER.getId() + "-hv"), output, GTIRef.ID, "", "antimatter_jackhammers",
                "has_battery_small", provider.hasSafeItem(BATTERIES_SMALL), jackhammer_lv.resolveStack(Material.NULL, Titanium, 0, 100000),
                ImmutableMap.<Character, Object>builder()
                        .put('R', PropertyIngredient.builder("primary").types(ROD_LONG).tool(AntimatterDefaultTools.JACKHAMMER, true).build())
                        .put('b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LARGE).build())
                        .put('E', PistonHV)
                        .put('S', PropertyIngredient.builder("secondary").mats(Titanium).types(SCREW).build())
                        .put('P', PLATE.getMaterialTag(Titanium))
                        .put('T', SPRING.getMaterialTag(Titanium))
                        .put('s', SCREWDRIVER.getTag()).build(), "SRs", "PTP", "EPb");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.JACKHAMMER.getId() + "-hv"), output, GTIRef.ID, AntimatterDefaultTools.JACKHAMMER.getId() + "_hv_from_pu", "antimatter_jackhammers",
                "has_power_unit_small", provider.hasSafeItem(POWER_UNIT_SMALL), jackhammer_lv.resolveStack(Material.NULL, Titanium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD_LONG).tool(AntimatterDefaultTools.JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "R", "b");
    }

    public static ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = new ItemStack(tool.getItem());
        tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
        if (!primary.has(MaterialTags.TOOLS)) return stack;
        Map<Enchantment, Integer> mainEnchants = MaterialTags.TOOLS.get(primary).toolEnchantment();
        if (!mainEnchants.isEmpty()) {
            mainEnchants.entrySet().stream().filter(e -> e.getKey().canEnchant(stack)).forEach(e -> stack.enchant(e.getKey(), e.getValue()));
        }
        return stack;
    }
}
