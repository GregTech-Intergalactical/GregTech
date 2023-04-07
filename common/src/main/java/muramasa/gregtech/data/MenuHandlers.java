package muramasa.gregtech.data;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.Ref;
import muramasa.gregtech.tile.multi.TileEntityFusionReactor;
import net.minecraft.world.entity.player.Inventory;

public class MenuHandlers {
    public static MenuHandlerMachine<TileEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "fusion_reactor";
        }
    };

    public static void init(){

    }
}
