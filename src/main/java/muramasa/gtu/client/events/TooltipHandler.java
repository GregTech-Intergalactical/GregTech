//package muramasa.gtu.client.events;
//
//import muramasa.gtu.Ref;
//import muramasa.antimatter.recipe.RecipeHelper;
//import net.minecraftforge.event.entity.player.ItemTooltipEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//public class TooltipHandler {
//
//    @SubscribeEvent
//    public static void onItemTooltip(ItemTooltipEvent e) {
//        if (Ref.SHOW_STACK_ORE_DICT) {
//            String[] names = RecipeHelper.getOres(e.getItemStack());
//            for (int i = 0; i < names.length; i++) {
//                e.getToolTip().add(names[i]);
//            }
//        }
//    }
//}
