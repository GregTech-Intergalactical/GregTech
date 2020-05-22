package muramasa.gti.cover;

import muramasa.antimatter.capability.impl.MachineCoverHandler;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.cover.CoverInstance;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.item.ItemCover;
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

public class CoverConveyor extends CoverTiered {

    public static String ID = "conveyor";

    static int[] speeds = {400,100,20,10,1};

    public CoverConveyor(Tier tier) {
        super(tier);
    }

    public CoverConveyor() {
        super();
    }

    @Override
    public String getId() {
        return tier == null ? ID : ID + "_" + tier.getId();
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public boolean onInteract(CoverInstance instance, PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
        if (!player.getEntityWorld().isRemote()) {
            NetworkHooks.openGui((ServerPlayerEntity) player, instance, packetBuffer -> {
                packetBuffer.writeBlockPos(instance.getTile().getPos());
                packetBuffer.writeInt(side.getIndex());
            });
        }
        return true;//super.onInteract(instance, player, hand, side, type);
    }


    @Override
    public void onPlace(CoverInstance instance, Direction side) {
        super.onPlace(instance, side);
    }

    //@Override
    //public Cover onPlace(ItemStack stack) {
    //    return new CoverConveyor(this.tier);
    //}

    @Override
    public void onUpdate(CoverInstance instance, Direction side) {
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (speeds[tier.getIntegerId()]) != 0) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        //DEBUG, just puts this item
        //((TileEntityMachine)instance.getTile()).itemHandler.get().addOutputs(new ItemStack(this.getItem(),1));
        Utils.transferItemsOnCap(instance.getTile(), adjTile);
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return new CoverConveyor(tier);
    }
}
