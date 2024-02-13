package muramasa.gregtech.items;

import muramasa.antimatter.item.ItemBasic;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

import muramasa.antimatter.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class ItemIntCircuit extends ItemBasic<ItemIntCircuit> {

    public final int circuitId;

    public ItemIntCircuit(String domain, String id, int circuitId, Properties properties) {
        super(domain, id, "int_circuits/", properties);
        this.circuitId = circuitId;
    }

    public ItemIntCircuit(String domain, String id,int circuitId) {
        super(domain, id, "int_circuits/");
        this.circuitId = circuitId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        int newId = playerIn.isCrouching() ? this.getNewCircuitIdBackward() : this.getNewCircuitIdForward();
        ItemStack stack = playerIn.getItemInHand(handIn);
        ItemStack newStack = new ItemStack(INT_CIRCUITS.get(newId).getItems()[0].getItem(),stack.getCount());
        playerIn.setItemInHand(handIn, newStack);
        return InteractionResultHolder.consume(stack);
    }

    private int getNewCircuitIdForward(){
        if (this.circuitId == 24){
            return 0;
        }
        return this.circuitId + 1;
    }
    private int getNewCircuitIdBackward(){
        if (this.circuitId == 0){
            return 24;
        }
        return this.circuitId - 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipComponents.add(Utils.translatable("tooltip.gti.int_circuit.0"));
        tooltipComponents.add(Utils.translatable("tooltip.gti.int_circuit.1"));
    }
}
