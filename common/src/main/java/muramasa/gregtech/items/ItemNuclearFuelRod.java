package muramasa.gregtech.items;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.IMaterialObject;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.CodeUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.single.BlockEntityNuclearReactorCore;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;

import java.util.List;

import static muramasa.gregtech.data.Materials.*;

public class ItemNuclearFuelRod extends ItemBasic<ItemNuclearFuelRod> implements IItemReactorRod, IMaterialObject {
    private final Material material;
    private final int emission, self, maximum, div;
    private final long durability;

    public ItemNuclearFuelRod(String domain, Material material, long durability, int emission, int self, int maximum, int div) {
        super(domain, material.getId() + "_nuclear_rod", new Properties().stacksTo(16).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.emission = emission;
        this.self = self;
        this.maximum = maximum;
        this.div = div;
        this.durability = durability;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 0){
            return material.getRGB();
        }
        return IItemReactorRod.super.getItemColor(stack, block, i);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission_info", Utils.translatable("tooltip.gti.nuclear_rod.emission_1").withStyle(ChatFormatting.GREEN)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self_info", Utils.translatable("tooltip.gti.nuclear_rod.self_1").withStyle(ChatFormatting.GREEN)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum_info", Utils.translatable("tooltip.gti.nuclear_rod.maximum_1").withStyle(ChatFormatting.GREEN)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor_info", Utils.translatable("tooltip.gti.nuclear_rod.factor_1").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.AQUA));
        long durability = stack.getTag() != null && stack.getTag().contains("rodDurability") ? stack.getTag().getLong("rodDurability") : this.durability;
        tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.remaining", Utils.literal(durability / 120000 + "").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.AQUA));
        if (level != null){
            int timeIndex = (int) ((level.getGameTime() / 100) % 10);
            switch (timeIndex){
                case 0 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.2", FluidPlatformUtils.INSTANCE.getFluidDisplayName(DistilledWater.getLiquid(1)), FluidPlatformUtils.INSTANCE.getFluidDisplayName(SemiheavyWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 1 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(HeavyWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(CodeUtils.divup(maximum, 8) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 2 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(TritiatedWater.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(CodeUtils.divup(maximum, 16) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.moderated.0", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 3 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(Tin.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.heat", "1/3").withStyle(ChatFormatting.GREEN));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 4 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(Sodium.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.heat", "1/6").withStyle(ChatFormatting.GREEN));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 5 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(Coolant.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div * 2)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    if (div <= 2) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 6 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(LithiumChloride.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(((emission - CodeUtils.divup(emission, 2))) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 5) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal((maximum + CodeUtils.divup(maximum, 4)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 7 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(ThoriumSalt.getLiquid(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission - CodeUtils.divup(emission, 2)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal( "0").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal((maximum * 4) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + (div - 1)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    if (div <= 5) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 8 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(CarbonDioxide.getGas(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal(emission + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal((self * 3) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
                case 9 -> {
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.when_used.1", FluidPlatformUtils.INSTANCE.getFluidDisplayName(Helium.getGas(1))).withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.emission", Utils.literal((emission - CodeUtils.divup(emission, 2)) + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.self", Utils.literal(self + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.maximum", Utils.literal(maximum + "").withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.nuclear_rod.neutrons").withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.GREEN));
                    tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.factor", Utils.literal("1/" + div).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW));
                    if (div <= 4) tooltipComponents.add(Utils.translatable("tooltip.gti.nuclear_rod.critical.0", Utils.translatable("tooltip.gti.nuclear_rod.critical.1").withStyle(level.getGameTime() % 20 <= 9 ? ChatFormatting.RED : ChatFormatting.WHITE)).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTIRef.ID, "item/basic/nuclear_fuel_rod"), new Texture(GTIRef.ID, "item/basic/empty_nuclear_fuel_rod")};
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean isReactorRod(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isModerated(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return stack.getTag() != null && stack.getTag().getBoolean("oldModerated");
    }

    @Override
    public void updateModeration(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("oldModerated", nbt.getBoolean("moderated"));
        nbt.putBoolean("moderated", false);
    }

    @Override
    public int getReactorRodNeutronEmission(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        int tNeutronOther = emission;
        int tNeutronSelf = self;
        int tNeutronDiv = div;
        FluidHolder coldCoolant = reactor.fluidHandler.map(f -> f.getInputTanks().getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
        if (coldCoolant.getFluid().is(Coolant.getFluidTag())) {
            tNeutronOther *= 4;
            tNeutronSelf *= 4;
            tNeutronDiv *= 2;
        } else if (coldCoolant.getFluid().is(CarbonDioxide.getFluidTag())) {
            tNeutronSelf *= 3;
        } else if (coldCoolant.getFluid().is(Helium.getFluidTag())) {
            tNeutronOther -= CodeUtils.divup(emission, 2);
        } else if (coldCoolant.getFluid().is(LithiumChloride.getFluidTag())) {
            tNeutronOther -= CodeUtils.divup(emission, 2);
            tNeutronSelf *= 5;
        } else if (coldCoolant.getFluid().is(ThoriumSalt.getFluidTag())) {
            tNeutronOther -= CodeUtils.divup(emission, 2);
            tNeutronSelf = 0;
            tNeutronDiv -= 1;
        } else if (coldCoolant.getFluid().is(Sodium.getFluidTag()) || coldCoolant.getFluid().is(Tin.getFluidTag())) {
            tNeutronDiv -= 1;
        }
        reactor.mNeutronCounts[slot] += tNeutronSelf;
        long tEmission = tNeutronOther + CodeUtils.divup(Math.max(reactor.oNeutronCounts[slot]-tNeutronSelf, 0), tNeutronDiv);
        return bindInt(tEmission);
    }

    private static int bindInt (long aBoundValue) {
        return (int)  Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, aBoundValue));
    }

    @Override
    public boolean getReactorRodNeutronReaction(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        reactor.heatHandler.ifPresent(h -> h.insertInternal(reactor.oNeutronCounts[slot], false));
        FluidHolder coldCoolant = reactor.fluidHandler.map(f -> f.getInputTanks().getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
        int tNeutronMax = getReactorRodNeutronMaximum(reactor, slot, stack);
        if (coldCoolant.getFluid().is(DistilledWater.getFluidTag()) || coldCoolant.getFluid().is(SemiheavyWater.getFluidTag())
                || coldCoolant.getFluid().is(HeavyWater.getFluidTag()) || coldCoolant.getFluid().is(TritiatedWater.getFluidTag())){
            stack.getOrCreateTag().putBoolean("moderated", true);
            stack.getOrCreateTag().putBoolean("oldModerated", true);
        }
        long durabilityLoss = reactor.oNeutronCounts[slot] < tNeutronMax ? 100 : CodeUtils.divup(400 * reactor.oNeutronCounts[slot], tNeutronMax);
        boolean oModerated = stack.getTag() != null && stack.getTag().getBoolean("oldModerated");
        if (oModerated) durabilityLoss *= 4;
        long durability = stack.getTag() != null && stack.getTag().contains("rodDurability") ? stack.getTag().getLong("rodDurability") : this.durability;
        durability = durabilityLoss > durability ? -1 : durability - durabilityLoss;
        stack.getOrCreateTag().putLong("rodDurability", durability);
        if (durability <= 0){
            ItemDepletedRod depletedRod = AntimatterAPI.get(ItemDepletedRod.class, material.getId() + "_depleted_rod", this.getDomain());
            if (depletedRod != null) {
                reactor.setRod(slot, new ItemStack(depletedRod));
            } else {
                stack.setCount(0);
            }
        }
        return true;
    }

    @Override
    public int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack, int neutrons, boolean moderated) {
        if (moderated){
            stack.getOrCreateTag().putBoolean("moderated", true);
        }
        reactor.mNeutronCounts[slot] += neutrons;
        return 0;
    }

    @Override
    public int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        FluidHolder coldCoolant = reactor.fluidHandler.map(f -> f.getInputTanks().getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
        if (coldCoolant.getFluid().is(LithiumChloride.getFluidTag())) {
            return (int) (maximum * CodeUtils.divup(maximum, 4));
        } else if (coldCoolant.getFluid().is(ThoriumSalt.getFluidTag())) {
            return maximum * 4;
        } else if (coldCoolant.getFluid().is(HeavyWater.getFluidTag())) {
            return (int) CodeUtils.divup(maximum, 8);
        } else if (coldCoolant.getFluid().is(TritiatedWater.getFluidTag())) {
            return (int) CodeUtils.divup(maximum, 16);
        } else {
            return maximum;
        }
    }
}
