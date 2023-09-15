package muramasa.gregtech.cover;

import muramasa.antimatter.cover.ICoverMode;

public enum ImportExportMode implements ICoverMode {
    EXPORT("Export",88, 24),
    IMPORT("Import",34, 24),
    EXPORT_IMPORT("Export Import",88, 42),
    IMPORT_EXPORT("Import Export",34, 42);

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

    public ImportExportMode next(){
        return switch (this){
            case EXPORT -> IMPORT;
            case IMPORT -> EXPORT_IMPORT;
            case EXPORT_IMPORT -> IMPORT_EXPORT;
            case IMPORT_EXPORT -> EXPORT;
        };
    }
    public ImportExportMode previous(){
        return switch (this){
            case IMPORT_EXPORT -> EXPORT_IMPORT;
            case EXPORT_IMPORT -> IMPORT;
            case IMPORT -> EXPORT;
            case EXPORT -> IMPORT_EXPORT;
        };
    }
}
