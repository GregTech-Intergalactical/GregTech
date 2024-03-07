package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.GregTechItems;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import tesseract.FluidPlatformUtils;
import tesseract.api.item.ExtendedItemContainer;

import static muramasa.antimatter.Ref.L;
import static muramasa.gregtech.data.Materials.SquidInk;

public class BlockEntityPrinter extends BlockEntityMachine<BlockEntityPrinter> implements IFilterableHandler {
    public BlockEntityPrinter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    MachineItemHandler<?> ih = itemHandler.orElse(null);
                    ExtendedItemContainer inputHandler = ih.getInputHandler();
                    ItemStack paper = inputHandler.getItem(0);
                    ItemStack stored = inputHandler.getItem(1);
                    if (paper.getItem() == Items.PAPER && paper.getCount() >= 3 && stored.getItem() == GregTechItems.DataStick){
                        CompoundTag prospect = stored.getTagElement("prospectData");
                        CompoundTag bookData = stored.getTagElement("bookData");
                        FluidHolder ink = fluidHandler.map(f -> f.getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
                        if (!ink.isEmpty() && ink.matches(SquidInk.getLiquid(20)) && ink.getFluidAmount() >= L){
                            ItemStack output = new ItemStack(GregTechItems.PrintedPages);
                            if (prospect != null && prospect.getBoolean("analyzed")){
                                CompoundTag nbt = output.getOrCreateTag();
                                nbt.putString("filtered_title", "Analyzed Prospection Data");
                                nbt.putString("title", "Analyzed Prospection Data");
                                BlockPos pos1 = BlockPos.of(prospect.getLong("pos"));
                                nbt.putString("author", "X: " + pos1.getX() + " Y: " + pos1.getY() + " Z: " + pos1.getZ() + " Dim: " + prospect.getString("dimension"));
                                nbt.putBoolean("resolved", true);
                                ListTag pages = new ListTag();
                                CompoundTag page = new CompoundTag();
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Prospection Data From:");
                                stringBuilder.append("\n").append("X: ").append(pos1.getX()).append(" Z: ").append(pos1.getZ()).append(" Dim: ").append(prospect.getString("dimension"));
                                stringBuilder.append("\n").append("Produces ");
                                if (prospect.contains("fluid")){
                                    CompoundTag fluid = prospect.getCompound("fluid");
                                    Fluid fluid1 = AntimatterPlatformUtils.getFluidFromID(new ResourceLocation(fluid.getString("name")));
                                    stringBuilder.append(fluid.getLong("maxYield")).append("L ").append(FluidPlatformUtils.INSTANCE.getFluidDisplayName(FluidHolder.of(fluid1)).getString());
                                } else {
                                    stringBuilder.append("No oil");
                                }
                                if (prospect.contains("ores")){
                                    CompoundTag ores = prospect.getCompound("ores");
                                    if (!ores.isEmpty()) {
                                        stringBuilder.append("\n Prospected Ores:\n");
                                        boolean addedFirst = false;
                                        for (String key : ores.getAllKeys()) {
                                            Material m = Material.get(key);
                                            if (m != Material.NULL){
                                                if (addedFirst){
                                                    stringBuilder.append(", ");
                                                }
                                                if (!addedFirst){
                                                    addedFirst = true;
                                                }
                                                stringBuilder.append(Utils.getLocalizedType(m));
                                            }
                                        }
                                    }
                                }
                                //page.putString("text", stringBuilder.toString());
                                pages.add(StringTag.valueOf(Component.Serializer.toJson(new TextComponent(stringBuilder.toString()))));
                                nbt.put("pages", pages);
                            } else if (bookData != null){
                                output.setTag(bookData.copy());
                            }
                            if ((prospect != null && prospect.getBoolean("analyzed")) || bookData != null){
                                return RecipeMaps.SCANNER.RB().recipeMapOnly().ii(RecipeIngredient.of(Items.PAPER, 3)).fi(SquidInk.getLiquid(L)).io(output).add("data_stick_book_printing", 400, 2);
                            }
                        }

                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == Items.PAPER || stack.getItem() == GregTechItems.DataStick;
            }

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                if (event == SlotType.IT_IN && data[0] instanceof Integer integer && integer == 1){
                    lastRecipe = null;
                }
                super.onMachineEvent(event, data);
            }
        });
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.IT_IN){
            return (slot == 0) == (stack.getItem() != GregTechItems.DataStick);
        }
        return true;
    }
}
