package muramasa.gregtech.items;

import muramasa.antimatter.registration.IColorHandler;
import muramasa.gregtech.blockentity.single.BlockEntityNuclearReactorCore;
import net.minecraft.world.item.ItemStack;

public interface IItemReactorRod extends IColorHandler {
    /** @return if a Reactor would accept this Item as a Part of it. */
    boolean isReactorRod(ItemStack stack);
    /** @return is a Reactor Rod is moderated and thus isn't usable for breeding */
    boolean isModerated(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack);
    /** Updates the moderation status of the reactor rod */
    void updateModeration(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack);
    /** @return the amount of Neutrons emitted by the Rod to each of the 4 surrounding Rods. Only called once every 20 ticks. */
    int getReactorRodNeutronEmission(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack);
    /** @return if the Reactor would be considered "Active" after calculating the Ticking of the Rod itself. */
    boolean getReactorRodNeutronReaction(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack);
    /** @return the Amount of reflected Neutrons after this Rod has been hit with neutrons. */
    int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack, int neutrons, boolean moderated);
    /** @return the Neutron Maximum for the lowest durability loss of this Rod */
    int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack);
    /** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
    //public ITexture getReactorRodTextureSides     (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
    /** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
    //public ITexture getReactorRodTextureTop       (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
}
