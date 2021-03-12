package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.gti.block.BlockCasing;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;

public class Parts {

    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        TIER_MATERIALS.forEach((t,m) -> {
            provider.addItemRecipe(output,"casings", "has_casing", provider.hasItem(WRENCH.getTag()),
                    AntimatterAPI.get(BlockCasing.class, "casing_"+t.getId()),
                    of('C', PLATE.getMaterialTag(m), 'W', WRENCH.getTag()), "CCC", "CWC", "CCC");
        });


        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            Item cable = TIER_CABLES.get(t);
            Item wire = TIER_WIRES.get(t);
            Material mat = TIER_MATERIALS.get(t);
            //Item smallGear = GEAR_SMALL.get(mat);
            Item smallGear = GEAR.get(mat);
            ITag.INamedTag<Item> plate = PLATE.getMaterialTag(mat);
            ITag.INamedTag<Item> rod = ROD.getMaterialTag(mat);
            //TODO: Circuits
            Item circuit = CircuitBoardBasic;
            Item motor = AntimatterAPI.get(ItemBasic.class, "motor_"+t.getId());
            Item piston = AntimatterAPI.get(ItemBasic.class, "piston_"+t.getId());
            Item robotArm = AntimatterAPI.get(ItemBasic.class, "robot_arm_"+t.getId());
            Item pump = AntimatterAPI.get(ItemCover.class, "pump_" + t.getId());
            Item conveyor = AntimatterAPI.get(ItemCover.class, "conveyor_" + t.getId());
            provider.addItemRecipe(output, "gtparts", "has_wrench", provider.hasItem(WRENCH.getTag()),
                   motor,
                   of('M', ROD.get(magnet), 'C', cable, 'W', wire, 'R', rod),
                    "CWR", "WMW", "RWC");
            provider.addItemRecipe(output, "gtparts", "has_wrench", provider.hasItem(WRENCH.getTag()),
                    piston,
                    of('M', motor, 'C', cable, 'G', smallGear,'P', plate, 'R', rod),
                    "PPP", "CRR", "CMG");
            provider.addItemRecipe(output, "gtparts", "has_wrench", provider.hasItem(WRENCH.getTag()),
                    conveyor,
                    of('M', motor, 'C', cable, 'P',PLATE.get(Rubber)),
                    "PPP", "MCM", "PPP");
            provider.addItemRecipe(output, "gtparts", "has_wrench", provider.hasItem(WRENCH.getTag()),
                    robotArm,
                    of('M', motor, 'C', cable, 'P',piston, 'I', circuit, 'R',rod),
                    "CCC", "MRM", "PIR");
            Material rotorMat = ((MaterialItem) TIER_ROTORS.get(t)).getMaterial();
            provider.addItemRecipe(output, "gtparts", "has_wrench", provider.hasItem(WRENCH.getTag()),
                    pump,
                    ImmutableMap.<Character, Object>builder()
                    .put('M',motor)
                    .put('C',cable)
                    .put('W', WRENCH.getTag())
                    .put('S', SCREWDRIVER.getTag())
                    .put('R', SCREW.get(rotorMat))
                    .put('T', TIER_ROTORS.get(t))
                    .put('O', t == Tier.IV ? RING.get(StyreneButadieneRubber) : RING.get(Rubber))
                    .put('P', TIER_PIPES.get(t))
                    .build()
                    ,"RTO","SPW","OMC");
        });

    }
}
