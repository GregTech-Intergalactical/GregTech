package muramasa.gregtech.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;

import java.util.List;

import static muramasa.gregtech.data.Materials.*;

public class ItemNuclearFuelRod extends ItemBasic<ItemNuclearFuelRod> implements IColorHandler {
    private final Material material;
    private final int emission, self, maximum, div;

    public ItemNuclearFuelRod(String domain, Material material, int durability, int emission, int self, int maximum, int div) {
        super(domain, material.getId() + "_nuclear_rod", new Properties().durability(durability).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.emission = emission;
        this.self = self;
        this.maximum = maximum;
        this.div = div;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 0){
            return material.getRGB();
        }
        return IColorHandler.super.getItemColor(stack, block, i);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (level != null){
            int timeIndex = (int) ((level.getGameTime() / 100) % 10);
            switch (timeIndex){
                case 0 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.2", FluidPlatformUtils.getFluidDisplayName(DistilledWater.getLiquid(1)), FluidPlatformUtils.getFluidDisplayName(SemiheavyWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 1 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(HeavyWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(Utils.divup(maximum, 8) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 2 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(TritiatedWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(Utils.divup(maximum, 16) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 3 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(Tin.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.heat", "1/3").withStyle(ChatFormatting.GREEN));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 4 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(Sodium.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.heat", "1/6").withStyle(ChatFormatting.GREEN));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 5 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(Coolant.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div * 2)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    if (div <= 2) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 6 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(LithiumChloride.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(((emission - Utils.divup(emission, 2))) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 5) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal((maximum + Utils.divup(maximum, 4)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 7 -> {
                    //tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(ThoriumSalt.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission - Utils.divup(emission, 2)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal( "0").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal((maximum * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 8 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(CarbonDioxide.getGas(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 3) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 9 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.getFluidDisplayName(Helium.getGas(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission - Utils.divup(emission, 2)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 2 == 0 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTIRef.ID, "item/basic/nuclear_fuel_rod"), new Texture(GTIRef.ID, "item/basic/empty_nuclear_fuel_rod")};
    }

    public int getEmission() {
        return emission;
    }

    public int getSelf() {
        return self;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getDiv() {
        return div;
    }

    public Material getMaterial() {
        return material;
    }
}
