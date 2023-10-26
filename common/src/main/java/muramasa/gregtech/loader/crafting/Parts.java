package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.GregTechTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.TagKey;
import java.util.Arrays;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.PLATES_IRON_ALUMINIUM;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.*;

public class Parts {
  public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
      tieredItems(output, provider);
      molds(output, provider);
      provider.shapeless(output, "fire_clay_dust", "parts", AntimatterMaterialTypes.DUST.get(Fireclay, 2),
              AntimatterMaterialTypes.DUST.getMaterialTag(Brick), AntimatterMaterialTypes.DUST.getMaterialTag(Clay));

      provider.addStackRecipe(output, GTIRef.ID, "drain_expensive", "parts",
              new ItemStack(GregTech.get(ItemCover.class, "drain"), 1), of('A', PLATES_IRON_ALUMINIUM, 'B', Items.IRON_BARS), "ABA", "B B", "ABA");

    provider.shapeless(output, "int_circuit", "gtparts",
            INT_CIRCUITS.get(0).getItems()[0], GTCoreTags.CIRCUITS_BASIC);
    // INT_CIRCUITS.forEach((k, v) -> {
    Ingredient ing = INT_CIRCUITS.get(0);
    provider.shapeless(output, "int_circuit_to_circuit", "gtparts",
        CircuitBasic.getDefaultInstance(), ing);
    // });

      provider.shapeless(output, GTIRef.ID, "", "carbon", DUST.get(FiberReinforcedEpoxyResin, 1), CarbonFibre, DUST.getMaterialTag(EpoxyResin));
      provider.shapeless(output, GTIRef.ID, "", "carbon", new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
      provider.addItemRecipe(output, GTIRef.ID, "", "carbon", CoalBall,
              of('F', Items.FLINT, 'C', DUST.getMaterialTag(Coal)), "CCC", "CFC", "CCC");
      provider.addItemRecipe(output, GTIRef.ID, "", "carbon", CoalChunk,
              of('F', Items.OBSIDIAN, 'C', CompressedCoalBall), "CCC", "CFC", "CCC");
      provider.addItemRecipe(output, GTIRef.ID, "","batteries", GTCoreItems.BatteryHullSmall, of(
              'P', PLATE.get(BatteryAlloy),
              'C', CABLE_GETTER.apply(PipeSize.VTINY, LV, false)
      ), "C", "P", "P");

      provider.addItemRecipe(output,  GTIRef.ID, "","batteries", GTCoreItems.BatteryHullMedium, of(
              'P', PLATE.get(BatteryAlloy),
              'C', CABLE_GETTER.apply(PipeSize.VTINY, MV, false)
      ), "C C", "PPP", "PPP");
      provider.addStackRecipe(output, GTIRef.ID, "", "batteries", DUST.get(Energium, 9),
              of('R', DUST.getMaterialTag(Redstone), 'r', DUST.getMaterialTag(Ruby)), "RrR", "rRr", "RrR");


      provider.addItemRecipe(output, GTIRef.ID, "diamondsaw_blade", "gtparts", DiamondSawBlade, of(
              'G', GEAR.get(CobaltBrass),
              'D', DUST_SMALL.get(Diamond)
      ), " D ", "DGD", " D ");

      provider.addItemRecipe(output, "mining_pipes", MINING_PIPE_THIN,
              of('H', HAMMER.getTag(), 'P', FLUID_PIPE_STEEL.getBlockItem(PipeSize.SMALL), 'F', FILE.getTag()), "HPF");
  }

  private static void tieredItems(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
      Arrays.stream(Tier.getStandard()).forEach(t -> {
          Material magnet = (t == Tier.ULV || t == LV) ? IronMagnetic
                  : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
          Object cable = CABLE_GETTER.apply(PipeSize.VTINY, t, false);
          Material mat = TIER_MATERIALS.get(t);
          // Item smallGear = GEAR_SMALL.get(mat);
          Item smallGear = GEAR.get(mat);
          TagKey<Item> plate = PLATE.getMaterialTag(mat);
          TagKey<Item> rod = ROD.getMaterialTag(mat);
          TagKey<Item> circuit = TIER_CIRCUITS.apply(t);

          Item motor = GregTech.get(ItemBasic.class, "motor_" + t.getId());
          Item piston = GregTech.get(ItemBasic.class, "piston_" + t.getId());
          Item robotArm = GregTech.get(ItemCover.class, "robot_arm_" + t.getId());
          Item emitter = GregTech.get(ItemBasic.class, "emitter_" + t.getId());
          Item sensor = GregTech.get(ItemBasic.class, "sensor_" + t.getId());
          Item pump = GregTech.get(ItemCover.class, "pump_" + t.getId());
          Item conveyor = GregTech.get(ItemCover.class, "conveyor_" + t.getId());
          Item fieldGen = GregTech.get(ItemBasic.class, "field_gen_" + t.getId());
          Object emitterRod = ROD.getMaterialTag(EMITTER_RODS.get(t));
          Object emitterGem = EMITTER_GEMS.get(t);
          provider.addItemRecipe(output, "gtparts", motor,
                  of('M', ROD.get(magnet), 'C', cable, 'W', WIRE_COPPER.getBlockItem(fromTier(t)), 'R', rod), "CWR", "WMW", "RWC");
          provider.addItemRecipe(output, "gtparts", piston,
                  of('M', motor, 'C', cable, 'G', smallGear, 'P', plate, 'R', rod), "PPP", "CRR", "CMG");
          provider.addItemRecipe(output, "gtparts", conveyor,
                  of('M', motor, 'C', cable, 'P', PLATE.get(Rubber)), "PPP", "MCM", "PPP");
          provider.addItemRecipe(output, "gtparts", robotArm,
                  of('M', motor, 'C', cable, 'P', piston, 'I', circuit, 'R', rod), "CCC", "MRM", "PIR");
          provider.addItemRecipe(output, "gtparts", emitter,
                  of('R', emitterRod, 'G', emitterGem, 'L', cable, 'C', circuit), "RRC", "LGR", "CLR");
          provider.addItemRecipe(output, "gtparts", sensor,
                  of('R', emitterRod, 'G', emitterGem, 'C', circuit, 'P', plate), "P G", "PR ", "CPP");
          PipeSize osmium = t == IV ? PipeSize.HUGE : PipeSize.values()[t.getIntegerId() - 1];
          Item center = t == LV ? Items.ENDER_PEARL : t == MV ? Items.ENDER_EYE : t == HV ? QuantumEye : t == EV ? Items.NETHER_STAR : QuantumStar;
          provider.addItemRecipe(output, "gtparts", fieldGen,
                  of('O', WIRE_OSMIUM.getBlockItem(osmium), 'C', circuit, 'G', center), "OCO", "CGC", "OCO");
          Material rotorMat = ((MaterialItem) TIER_ROTORS.get(t)).getMaterial();
          provider.addItemRecipe(output, "gtparts", pump,
                  ImmutableMap.<Character, Object>builder().put('M', motor).put('C', cable).put('W', WRENCH.getTag())
                          .put('S', SCREWDRIVER.getTag()).put('R', SCREW.get(rotorMat)).put('T', TIER_ROTORS.get(t))
                          .put('O', RING.get(Rubber)).put('P', TIER_PIPES.get(t).apply(PipeSize.NORMAL))
                          .build(),
                  "RTO", "SPW", "OMC");
      });
  }

  private static void molds(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
      provider.addItemRecipe(output, GTIRef.ID, "empty_shape", "gtparts", EmptyShape, of(
              'P', PLATE.get(Steel),
              'H', HAMMER.getTag(),
              'F', FILE.getTag()
      ), "HF","PP", "PP");
      moldRecipe(output, provider, MoldPlate, "H", "P");
      moldRecipe(output, provider, MoldIngot, "P", "H");
      moldRecipe(output, provider, MoldCasing, " H", "P ");
      moldRecipe(output, provider, MoldGear, "PH");
      moldRecipe(output, provider, MoldCoinage, "H ", " P");
      moldRecipe(output, provider, MoldBottle, "P ", " H");
      moldRecipe(output, provider, MoldBall, " P", "H ");
      moldRecipe(output, provider, MoldBlock, "HP");
      moldRecipe(output, provider, MoldNugget, "P H");
      //moldRecipe(output, provider, MoldBuns, "P  ", "  H");
      //moldRecipe(output, provider, MoldBread, "P  ", "   ", "  H");
      //moldRecipe(output, provider, MoldBaguettes, "P ", "  ", " H");
      moldRecipe(output, provider, MoldAnvil, " P", "  ", "H ");
      moldRecipe(output, provider, MoldGearSmall, "H P");

      shapeRecipe(output, provider, ShapePlate, "HP");
      shapeRecipe(output, provider, ShapeRod, "P ", " H");
      shapeRecipe(output, provider, ShapeBolt, "H ", " P");
      shapeRecipe(output, provider, ShapeRing, "PH");
      shapeRecipe(output, provider, ShapeCell, "H", "P");
      shapeRecipe(output, provider, ShapeIngot, " H", "P ");
      shapeRecipe(output, provider, ShapeWire, "P", "H");
      shapeRecipe(output, provider, ShapeCasing, " P", "H ");
      shapeRecipe(output, provider, ShapePipeTiny, " H", "  ", "P ");
      shapeRecipe(output, provider, ShapePipeSmall, "P  ", "  H");
      shapeRecipe(output, provider, ShapePipeNormal, "P ", "  ", " H");
      shapeRecipe(output, provider, ShapePipeLarge, "P  ", "   ", "  H");
      shapeRecipe(output, provider, ShapePipeHuge, "  H", "   ", "P  ");
      shapeRecipe(output, provider, ShapeBlock, "P H");
      shapeRecipe(output, provider, ShapeHeadSword, "P", " ", "H");
      shapeRecipe(output, provider, ShapeHeadPickaxe, " P", "  ", "H ");
      shapeRecipe(output, provider, ShapeHeadShovel, "  P", "   ", "H  ");
      shapeRecipe(output, provider, ShapeHeadAxe, "  P", "H  ");
      shapeRecipe(output, provider, ShapeHeadHoe, "H P");
      shapeRecipe(output, provider, ShapeHeadHammer, "H  ", "  P");
      shapeRecipe(output, provider, ShapeHeadFile, "H  ", "   ", "  P");
      shapeRecipe(output, provider, ShapeHeadSaw, "H ", "  ", " P");
      shapeRecipe(output, provider, ShapeGear, "H", " ", "P");
      //shapeRecipe(output, provider, ShapeBottle, "  H", "P  ");
      shapeRecipe(output, provider, ShapeGearSmall, "H", "P");

  }

  private static void moldRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Item mold, String... shapes){
      provider.addItemRecipe(output, GTIRef.ID, "", "gtparts", mold,
              of('P', EmptyShape, 'H', HAMMER.getTag()), shapes);
  }

    private static void shapeRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Item mold, String... shapes){
        provider.addItemRecipe(output, GTIRef.ID, "", "gtparts", mold,
                of('P', EmptyShape, 'H', WIRE_CUTTER.getTag()), shapes);
    }

  static PipeSize fromTier(Tier tier){
      if (tier == LV) return PipeSize.VTINY;
      if (tier == MV) return PipeSize.TINY;
      if (tier == HV) return PipeSize.SMALL;
      if (tier == IV) return PipeSize.HUGE;
      return PipeSize.NORMAL;
  }
}
