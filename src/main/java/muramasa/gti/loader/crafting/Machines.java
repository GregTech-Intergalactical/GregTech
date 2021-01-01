package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.function.Consumer;

import static muramasa.antimatter.Data.ROTOR;
import static muramasa.gti.data.Machines.*;
import static muramasa.gti.data.Materials.Tin;

public class Machines {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = AntimatterAPI.get(ItemBasic.class, "motor_"+tier.getId());
            if (motor == null) return;
            Item piston = AntimatterAPI.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = AntimatterAPI.get(ItemBasic.class, "robot_arm_"+tier.getId());
            if (arm == null) return;
            Item conveyor = AntimatterAPI.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            Item pump = AntimatterAPI.get(ItemCover.class, "pump_"+tier.getId());
            if (pump == null) return;
            Item casing = Item.BLOCK_TO_ITEM.get(AntimatterAPI.get(BlockCasing.class, "casing_"+tier.getId()));
            if (casing == null) return;
            Item circuit = GregTechData.CircuitBasic;
            Item cable = GregTechData.TIER_CABLES.get(tier);
            if (cable == null) return;
            Item glass = Items.GLASS;
            Item rotor = ROTOR.get(Tin);//TODO: Better
            Item machine = BENDER.getItem(tier);

            if (machine != null) {
                provider.addItemRecipe(output, "machines", "has_motor", provider.hasItem(GregTechData.MotorLV), machine,
                        ImmutableMap.of(
                                'P', piston,
                                'M', motor,
                                'C', circuit,
                                'L', cable,
                                'H', casing
                        ), "PLP", "CHC", "MLM");
            }
            machine = WIRE_MILL.getItem(tier);
            if (machine != null) {
                provider.addItemRecipe(output, "machines", "has_motor", provider.hasItem(GregTechData.MotorLV), machine,
                        ImmutableMap.of(
                                'M', motor,
                                'C', circuit,
                                'L', cable,
                                'H', casing
                        ), "MLM", "CHC", "MLM");
            }
            machine = ASSEMBLER.getItem(tier);
            if (machine != null) {
                provider.addItemRecipe(output, "machines", "has_motor", provider.hasItem(GregTechData.MotorLV), machine,
                        ImmutableMap.of(
                                'R', arm,
                                'O', conveyor,
                                'C', circuit,
                                'L', cable,
                                'H', casing
                        ), "RCR", "OHO", "LCL");
            }
            machine = CENTRIFUGE.getItem(tier);
            if (machine != null) {
                provider.addItemRecipe(output, "machines", "has_motor", provider.hasItem(GregTechData.MotorLV), machine,
                        ImmutableMap.of(
                                'M', motor,
                                'C', circuit,
                                'L', cable,
                                'H', casing
                        ), "CMC", "LHL", "CHC");
            }
            machine = MIXER.getItem(tier);
            if (machine != null) {
                provider.addItemRecipe(output, "machines", "has_motor", provider.hasItem(GregTechData.MotorLV), machine,
                        ImmutableMap.of(
                                'M', motor,
                                'R',rotor,
                                'G', glass,
                                'C', circuit,
                                'H', casing
                        ), "GRG", "GMG", "CHC");
            }
        });
    }
}
