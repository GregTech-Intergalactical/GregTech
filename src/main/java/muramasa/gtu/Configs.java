package muramasa.gtu;

//@Config(modid = Ref.MODID, name = "GregTech/" + Ref.NAME, category = "")
public class Configs {

	public static Jei JEI = new Jei();
	public static Gameplay GAMEPLAY = new Gameplay();
	public static Data DATA = new Data();
	public static Recipe RECIPE = new Recipe();
	public static World WORLD = new World();
	public static ModCompatibility MODCOMPAT = new ModCompatibility();
	public static Misc MISC = new Misc();

	public static class Jei {
		//@RequiresMcRestart
		//@Comment("Show all GT items - Default: false")
		public boolean SHOW_ALL_MATERIAL_ITEMS = false;

		//@RequiresMcRestart
		//@Comment("Show all filled fluid cells - Default: true")
		public boolean SHOW_ALL_FLUID_CELLS = true;
	}

	public static class Gameplay {
		//@RequiresMcRestart
		//@Comment("Enable hardcore cable loss and voltage - Default: true")
		public boolean HARDCORE_CABLES = true;

		//@RequiresMcRestart
		//@Comment("Allow GT Axes to fell trees - Default: true")
		public boolean AXE_TIMBER = true;

		//@RequiresMcRestart
		//@Config.RangeInt(min = 1, max = 100)
		//@Comment("Max height of a column of logs a GT Axe will fell - Default: 20")
		public int AXE_TIMBER_MAX = 20;
	}

	public static class Data {
		//@RequiresMcRestart
		//@Comment("Generate all of GT's Material Items, even if they're unused - Default: false")
		public boolean ENABLE_ALL_MATERIAL_ITEMS = false;

		//@RequiresMcRestart
		//@Comment("Replace some GT items with other variants, eg: Vanilla gold ingots - Default: true")
		public boolean ENABLE_ITEM_REPLACEMENTS = true;
	}

	public static class Recipe {
		//@RequiresMcRestart
		//@Comment("Enables automatic recipe output unification controlled by MOD_PRIORITY - Default: true")
		public boolean ENABLE_RECIPE_UNIFICATION = true;

		//@RequiresMcRestart
		//@Comment("Allows unification to prefer items from other mods in the given order")
		public String[] MOD_PRIORITY = new String[0];
	}

	public static class World {
		//@RequiresWorldRestart
		//@Comment("Disable Vanilla ore generation (Iron Ore, Diamond Ore etc) - Default: true")
		public boolean DISABLE_VANILLA_ORE_GEN = true;

		//@RequiresWorldRestart
		//@Comment("Disable vanilla stone generation (Granite, Diorite etc) - Default: false")
		public boolean DISABLE_VANILLA_STONE_GEN = true;

		//@RequiresWorldRestart
		public int ORE_VEIN_MAX_SIZE = 32;

		//@RequiresWorldRestart
		//@Comment("Control percentage of filled 3x3 chunks. Lower number means less oreveins spawn")
		public int ORE_VEIN_CHANCE = 100;

		//@RequiresWorldRestart
		//@Comment("Control number of attempts to find a valid orevein. Generally this maximum limit isn't hit, selecting a vein is cheap")
		public int ORE_VEIN_FIND_ATTEMPTS = 64;

		//@RequiresWorldRestart
		//@Comment("Control number of attempts to place a valid orevein.  If a vein wasn't placed due to height restrictions, completely in the water, etc, another attempt is tried")
		public int ORE_VEIN_PLACE_ATTEMPTS = 8;

		//@RequiresWorldRestart
		//@Comment("Whether or not to place small ores as placer ores for an orevein")
		public boolean ORE_VEIN_SMALL_ORE_MARKERS = false;

		//@RequiresWorldRestart
		//@Comment("//Multiplier to control how many placer ores get generated")
		public int ORE_VEIN_SMALL_ORE_MARKERS_MULTI = 2;

		//@RequiresWorldRestart
		public boolean ORE_VEIN_SPECTATOR_DEBUG = true;

		//@RequiresWorldRestart
		public boolean ORE_JSON_RELOADING = true;
	}

	public static class ModCompatibility {
		//@RequiresMcRestart
		//@Comment("Enable all mod support registrars - Default: true")
		public boolean ENABLE_ALL_REGISTRARS = true;

		//@Comment("Have Underground Biomes Stones interchangeable with GregTech native ones (Such as marble, red granite etc), this may break balance - Default: false")
		public boolean ENABLE_UB_CROSS_OREDICT = false;
	}

	public static class Misc {
		//@RequiresMcRestart
		//@Comment("Enable flat machine related models (5U Style) - Default: false")
		public boolean BASIC_MACHINE_MODELS = false;
	}
}
