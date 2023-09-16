package muramasa.gregtech.data;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.multi.BlockEntityFusionReactor;
import net.minecraft.world.entity.player.Inventory;

public class MenuHandlers {
    public static MenuHandlerMachine<BlockEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(GTIRef.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMachine ? new ContainerMultiMachine((BlockEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public String screenDomain() {
            return GTIRef.ID;
        }

        @Override
        public String screenID() {
            return "fusion_reactor";
        }
    };

    public static void init(){

    }
}
