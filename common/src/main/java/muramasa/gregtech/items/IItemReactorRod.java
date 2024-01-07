package muramasa.gregtech.items;

import muramasa.antimatter.registration.IColorHandler;
import muramasa.gregtech.blockentity.single.BlockEntityNuclearReactorCore;
import net.minecraft.world.item.ItemStack;

public interface IItemReactorRod extends IColorHandler {
    /** @return if a Reactor would accept this Item as a Part of it. */
    boolean isReactorRod(ItemStack aStack);
    /** @return is a Reactor Rod is moderated and thus isn't usable for breeding */
    boolean isModerated(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack);
    /** Updates the moderation status of the reactor rod */
    void updateModeration(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack);
    /** @return the amount of Neutrons emitted by the Rod to each of the 4 surrounding Rods. Only called once every 20 ticks. */
    int getReactorRodNeutronEmission(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack);
    /** @return if the Reactor would be considered "Active" after calculating the Ticking of the Rod itself. */
    boolean getReactorRodNeutronReaction(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack);
    /** @return the Amount of reflected Neutrons after this Rod has been hit with aNeutrons. */
    int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated);
    /** @return the Neutron Maximum for the lowest durability loss of this Rod */
    int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore aReactor, int aSlot, ItemStack aStack);
    /** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
    //public ITexture getReactorRodTextureSides     (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
    /** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
    //public ITexture getReactorRodTextureTop       (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
}
