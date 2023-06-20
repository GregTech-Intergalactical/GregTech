package muramasa.gregtech.cover;

import muramasa.antimatter.cover.ICoverMode;

public enum ImportExportMode implements ICoverMode {
    EXPORT("Export",88, 24),
    IMPORT("Import",34, 24),
    EXPORT_CONDITIONAL("Export Conditional",106, 24),
    IMPORT_CONDITIONAL("Import Conditional",52, 24),
    EXPORT_INVERT_COND("Export Invert Conditional",124, 24),
    IMPORT_INVERT_COND("Import Invert Conditional",70, 24),
    EXPORT_IMPORT("Export Import",88, 42),
    IMPORT_EXPORT("Import Export",34, 42),
    EXPORT_IMPORT_CONDITIONAL("Export Import Conditional",106, 42),
    IMPORT_EXPORT_CONDITIONAL("Import Export Conditional",52, 42),
    EXPORT_IMPORT_INVERT_COND("Export Import Invert Conditional",124, 42),
    IMPORT_EXPORT_INVERT_COND("Import Export Invert Conditional",70, 42);

    int x, y;
    String name;

    ImportExportMode(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }
}
