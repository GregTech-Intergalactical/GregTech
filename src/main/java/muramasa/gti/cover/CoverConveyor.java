package muramasa.gti.cover;

import muramasa.antimatter.capability.impl.MachineCoverHandler;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import muramasa.gti.data.Data;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;

public class CoverConveyor extends Cover {

    private static String ID = "conveyor";

    static int[] speeds = {200,150,100,50,25,10,5,1};

    public CoverConveyor(Tier tier) {
        this.tier = tier;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public boolean onInteract(TileEntity tile, PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
        if (tile == null) {
            this.tile = tile;
        }
        NetworkHooks.openGui((ServerPlayerEntity) player, this, packetBuffer -> {
            packetBuffer.writeBlockPos(tile.getPos());
            packetBuffer.writeInt(side.getIndex());
        });
        return super.onInteract(tile, player, hand, side, type);
    }

    @Override
    public ItemStack getDroppedStack() {
        //TODO maybe a better way to do this? but for now, it works.
        //TODO coverStacks should probably be tier sensitive? this would mean
        //TODO all covers would need a tier member
        switch (tier.getId()) {
            case "lv": return Data.ConveyorLV.get(1);
            case "mv": return Data.ConveyorMV.get(1);
            case "hv": return Data.ConveyorHV.get(1);
            case "ev": return Data.ConveyorEV.get(1);
            case "iv": return Data.ConveyorIV.get(1);
            default: return Data.ConveyorLV.get(1);
        }
    }

    @Override
    public void onPlace(TileEntity tile, Direction side) {
        this.tile = tile;
        super.onPlace(tile, side);
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, @Nonnull PlayerInventory inv, @Nonnull PlayerEntity player) {
        this.gui = new GuiData<Cover>(this.getDomain(),ID, muramasa.antimatter.Data.COVER_MENU_HANDLER);
        return gui.getMenuHandler().getMenu(this, inv, windowId);
    }

    @Override
    public Cover onPlace(ItemStack stack) {
        return new CoverConveyor(this.tier);
    }

    @Override
    public void onUpdate(TileEntity tile, Direction side) {
        if (tile.getWorld() == null || tile.getWorld().getGameTime() % (speeds[tier.getIntegerId()]) != 0) return;
        TileEntity adjTile = tile.getWorld().getTileEntity(tile.getPos().offset(side));
        if (adjTile == null) return;
        //if (!tile.has(MachineFlag.ITEM))  return;
        adjTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(x -> {
         //   Utils.transferItemsIfExists(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).););
        });
       // Utils.transferItems(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite()), adjTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite()));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Conveyor");
    }

    @Override
    public boolean hasGui() {
        return true;
    }
}
