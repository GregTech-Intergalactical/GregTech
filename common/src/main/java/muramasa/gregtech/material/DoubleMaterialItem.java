package muramasa.gregtech.material;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ISharedAntimatterObject;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DoubleMaterialItem extends ItemBasic<MaterialItem> implements ISharedAntimatterObject, IColorHandler, ITextureProvider, IModelProvider {
    protected Material material,  innerMaterial;
    protected MaterialType<?> type;
    public DoubleMaterialItem(String domain, MaterialType<?> type, Material material, Material innerMaterial, Properties properties) {
        super(domain, type.getId() + "_" + material.getId() + "_" + innerMaterial.getId(), DoubleMaterialItem.class, properties);
        this.material = material;
        this.innerMaterial = innerMaterial;
        this.type = type;
    }

    public DoubleMaterialItem(String domain, MaterialType<?> type, Material material, Material innerMaterial) {
        this(domain, type, material, innerMaterial, new Properties().tab(Ref.TAB_MATERIALS));
    }

    public MaterialType<?> getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    public Material getInnerMaterial() {
        return innerMaterial;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (allowdedIn(group) && getType().isVisible()) items.add(new ItemStack(this));
    }

    public static void addTooltipsForMaterialItems(ItemStack stack, Material mat, MaterialType<?> type, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (!mat.getChemicalFormula().isEmpty()) {
            if (Screen.hasShiftDown()) {
                tooltip.add(new TranslatableComponent("antimatter.tooltip.chemical_formula").append(": ").append(new TextComponent(mat.getChemicalFormula()).withStyle(ChatFormatting.DARK_AQUA)));
                tooltip.add(new TranslatableComponent("antimatter.tooltip.mass").append(": ").append(new TextComponent(mat.getMass() + "").withStyle(ChatFormatting.DARK_AQUA)));
                tooltip.add(new TranslatableComponent("antimatter.tooltip.material_modid", AntimatterPlatformUtils.getModName(mat.materialDomain())));
            } else {
                tooltip.add(new TranslatableComponent("antimatter.tooltip.formula").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}
