package muramasa.gregtech;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.machine.types.Machine;

import java.util.Map;

import static muramasa.gregtech.data.Machines.*;

public class GTRemapping {
    private static final Map<String, String> REMAPPING_MAP = new Object2ObjectArrayMap<>();

    private static final Map<String, String> BE_REMAPPING_MAP = new Object2ObjectArrayMap<>();

    public static void init(){
        REMAPPING_MAP.put("coke_oven_bronze", "coke_oven");
        REMAPPING_MAP.put("primitive_blast_furnace_bronze", "primitive_blast_furnace");
        remapMachine("pulverizer", MACERATOR);
        remapMachine("hatch_item_input", HATCH_ITEM_I);
        remapMachine("steam_generator", STEAM_GENERATOR);
        remapMachine("gas_generator", GAS_GENERATOR);
        remapMachine("battery_buffer_one", BATTERY_BUFFER_ONE);
        remapMachine("battery_buffer_four", BATTERY_BUFFER_FOUR);
        remapMachine("battery_buffer_nine", BATTERY_BUFFER_EIGHT);
        REMAPPING_MAP.put("circuit_basic", "basic_integrated_circuit");
        REMAPPING_MAP.put("circuit_basic_electronic", "basic_electronic_circuit");
        REMAPPING_MAP.put("circuit_good", "good_electronic_circuit");
        REMAPPING_MAP.put("circuit_adv", "advanced_circuit");
        REMAPPING_MAP.put("circuit_nanoprocessor", "nanoprocessor");
        REMAPPING_MAP.put("circuit_quantumprocessor", "quantumprocessor");
        REMAPPING_MAP.put("circuit_energy_flow", "energy_flow_circuit");
        REMAPPING_MAP.put("circuit_wetware", "wetware_circuit");
        REMAPPING_MAP.put("vacuumtube", "vacuum_tube");
        REMAPPING_MAP.put("adv_circuit_parts", "advanced_circuit_parts");
        REMAPPING_MAP.put("battery_hull_small", "small_battery_hull");
        REMAPPING_MAP.put("battery_hull_medium", "medium_battery_hull");
        REMAPPING_MAP.put("battery_hull_large", "large_battery_hull");
        REMAPPING_MAP.put("battery_small_mercury", "small_mercury_battery");
        REMAPPING_MAP.put("battery_small_acid", "small_acid_battery");
        REMAPPING_MAP.put("battery_small_cadmium", "small_cadmium_battery");
        REMAPPING_MAP.put("battery_small_lithium", "small_lithium_battery");
        REMAPPING_MAP.put("battery_small_sodium", "small_sodium_battery");
        REMAPPING_MAP.put("battery_medium_mercury", "medium_mercury_battery");
        REMAPPING_MAP.put("battery_medium_acid", "medium_acid_battery");
        REMAPPING_MAP.put("battery_medium_cadmium", "medium_cadmium_battery");
        REMAPPING_MAP.put("battery_medium_lithium", "medium_lithium_battery");
        REMAPPING_MAP.put("battery_medium_sodium", "medium_sodium_battery");
        REMAPPING_MAP.put("battery_large_mercury", "large_mercury_battery");
        REMAPPING_MAP.put("battery_large_acid", "large_acid_battery");
        REMAPPING_MAP.put("battery_large_cadmium", "large_cadmium_battery");
        REMAPPING_MAP.put("battery_large_lithium", "large_lithium_battery");
        REMAPPING_MAP.put("battery_large_sodium", "large_sodium_battery");
        REMAPPING_MAP.put("coil_cupronickel", "cupronickel_coil");
        REMAPPING_MAP.put("coil_kanthal", "kanthal_coil");
        REMAPPING_MAP.put("coil_nichrome", "nichrome_coil");
        REMAPPING_MAP.put("coil_tungstensteel", "tungstensteel_coil");
        REMAPPING_MAP.put("coil_hssg", "hssg_coil");
        REMAPPING_MAP.put("coil_naquadah", "naquadah_coil");
        REMAPPING_MAP.put("coil_naquadah_alloy", "naquadah_alloy_coil");
        REMAPPING_MAP.put("coil_fusion", "fusion_coil");
        REMAPPING_MAP.put("coil_superconductor", "superconductor_coil");
        REMAPPING_MAP.put("casing_bronze", "bronze_casing");
        REMAPPING_MAP.put("casing_bricked_bronze", "bricked_bronze_casing");
        REMAPPING_MAP.put("casing_steel", "steel_casing");
        REMAPPING_MAP.put("casing_bricked_steel", "bricked_steel_casing");
        REMAPPING_MAP.put("casing_bronze_plated_brick", "bronze_plated_brick_casing");
        REMAPPING_MAP.put("casing_solid_steel", "solid_steel_casing");
        REMAPPING_MAP.put("casing_stainless_steel", "stainless_steel_casing");
        REMAPPING_MAP.put("casing_titanium", "titanium_casing");
        REMAPPING_MAP.put("casing_tungstensteel", "tungstensteel_casing");
        REMAPPING_MAP.put("casing_heat_proof", "heat_proof_casing");
        REMAPPING_MAP.put("casing_frost_proof", "frost_proof_casing");
        REMAPPING_MAP.put("casing_radiation_proof", "radiation_proof_casing");
        REMAPPING_MAP.put("casing_firebox_bronze", "bronze_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_steel", "steel_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_titanium", "titanium_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_tungstensteel", "tungstensteel_firebox_casing");
        REMAPPING_MAP.put("casing_gearbox_bronze", "bronze_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_steel", "steel_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_titanium", "titanium_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_tungstensteel", "tungstensteel_gearbox_casing");
        REMAPPING_MAP.put("casing_pipe_bronze", "bronze_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_steel", "steel_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_titanium", "titanium_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_tungstensteel", "tungstensteel_pipe_casing");
        REMAPPING_MAP.put("casing_engine_intake", "engine_intake_casing");
        REMAPPING_MAP.put("casing_turbine_1", "steel_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_2", "stainless_steel_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_3", "titanium_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_4", "tungstensteel_turbine_casing");
    }
    private static void remapMachine(String old, Machine<?> machine){
        BE_REMAPPING_MAP.put(old, machine.getId());
        machine.getTiers().forEach(t -> {
            REMAPPING_MAP.put(old + "_" + t.getId(), machine.getId() + "_" + t.getId());
        });
    }

    public static Map<String, String> getBeRemappingMap() {
        return BE_REMAPPING_MAP;
    }

    public static Map<String, String> getRemappingMap() {
        return REMAPPING_MAP;
    }
}
