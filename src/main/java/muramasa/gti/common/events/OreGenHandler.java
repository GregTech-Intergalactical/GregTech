//package muramasa.gtu.common.events;
//
//import muramasa.gtu.Configs;
//import muramasa.gtu.data.Materials;
//import muramasa.antimatter.worldgen.WorldGenHelper;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.eventbus.api.Event;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//import java.util.Arrays;
//import java.util.EnumSet;
//
//public class OreGenHandler {
//
//    private static EnumSet<OreGenEvent.GenerateMinable.EventType> PREVENTED_TYPES = EnumSet.noneOf(OreGenEvent.GenerateMinable.EventType.class);
//
//    public static void init() {
//        if (Configs.WORLD.DISABLE_VANILLA_ORE_GEN) PREVENTED_TYPES.addAll(Arrays.asList(COAL, IRON, GOLD, DIAMOND, EMERALD, REDSTONE, LAPIS, QUARTZ));
//        if (Configs.WORLD.DISABLE_VANILLA_STONE_GEN) PREVENTED_TYPES.addAll(Arrays.asList(ANDESITE, DIORITE, GRANITE));
//        if (PREVENTED_TYPES.size() > 0) MinecraftForge.ORE_GEN_BUS.register(OreGenHandler.class);
//    }
//
//    @SubscribeEvent
//    public static void onOreGenMineable(OreGenEvent.GenerateMinable e) {
//        e.setResult(e.getGenerator() instanceof WorldGenMinable && PREVENTED_TYPES.contains(e.getType()) ? Event.Result.DENY : e.getResult());
//        if (e.getType() == GRAVEL) WorldGenHelper.setRock(e.getWorld(), e.getWorld().getTopSolidOrLiquidBlock(e.getPos()), Materials.Flint);
//    }
//}
