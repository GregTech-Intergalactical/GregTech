package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTools;
import io.github.gregtechintergalactical.gtcore.item.ItemPowerUnit;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.recipe.material.MaterialRecipe;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.items.ItemTurbineRotor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import tesseract.TesseractCapUtils;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IGTNode;

import java.util.Map;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.data.AntimatterMaterialTypes.SCREW;
import static muramasa.antimatter.material.Material.NULL;
import static muramasa.gregtech.data.GregTechData.*;

public class ToolTypes {

    public static final MaterialTypeItem<?> TURBINE_BLADE = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("turbine_blade", 2, true, (Ref.U * 3) + (Ref.U8 * 2)));//.unSplitName();
    public static final MaterialTypeItem<?> SMALL_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("small_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 2));
    public static final MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 4));
    public static final MaterialTypeItem<?> LARGE_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("large_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 6));
    public static final MaterialTypeItem<?> HUGE_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("huge_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 8));
    public static final AntimatterToolType SMALL_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GTIRef.ID, "small_turbine_rotor", 1, 1, 1, -1.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(SMALL_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GTIRef.ID, "turbine_rotor", 1, 1, 1, 1.5F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(BROKEN_TURBINE_ROTOR).setDurabilityMultiplier(2).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType LARGE_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GTIRef.ID, "large_turbine_rotor", 1, 1, 1, 4.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(LARGE_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setDurabilityMultiplier(3).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType HUGE_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GTIRef.ID, "huge_turbine_rotor", 1, 1, 1, 2.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(HUGE_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setDurabilityMultiplier(4).setToolSupplier(ItemTurbineRotor::new);
    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", GTIRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Material m = (Material) mats.mats.get("secondary");
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            String domain = Ref.ID;
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'));
            return type.resolveStack((Material) mats.mats.get("primary"), m == null ? NULL : m, battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            CompoundTag nbt = stack.getOrCreateTagElement(muramasa.antimatter.Ref.TAG_TOOL_DATA);
            Material primary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider UNIT_POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool-from-unit", GTIRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Tuple<Long, Material>> t = (Tuple<Long, Tuple<Long, Material>>) mats.mats.get("secondary");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'));
            t.getB().getB();
            return type.resolveStack((Material) mats.mats.get("primary"), t.getB().getB(), t.getA(), t.getB().getA());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            return ImmutableMap.of();
        }
    });
    static {
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_RE.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_SMALL.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_MEDIUM.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_LARGE.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_LV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_MV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_HV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_SMALL.location(), ToolTypes::getEnergyAndMat);
    }

    public static void init(){
        TURBINE_BLADE.unSplitName().setIgnoreTextureSets();
        SMALL_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        LARGE_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        HUGE_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        SMALL_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        LARGE_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        HUGE_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        TURBINE_BLADE.dependents(SCREW, PLATE);
    }

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery battery){
            long energy = TesseractCapUtils.getEnergyHandlerItem(stack).map(IGTNode::getEnergy).orElse((long)0);
            long maxEnergy = TesseractCapUtils.getEnergyHandlerItem(stack).map(IGTNode::getCapacity).orElse(battery.getCapacity());
            return new Tuple<>(energy, maxEnergy);
        }
        if (stack.getItem() instanceof IAntimatterTool tool){
            if (tool.getAntimatterToolType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        return null;
    }

    public static Tuple<Long, Tuple<Long, Material>> getEnergyAndMat(ItemStack stack){
        if (stack.getItem() instanceof ItemPowerUnit tool){
            long currentEnergy = tool.getCurrentEnergy(stack);
            long maxEnergy = tool.getMaxEnergy(stack);
            return new Tuple<>(currentEnergy, new Tuple<>(maxEnergy, tool.getMaterial(stack)));
        }
        return null;
    }
}
