package muramasa.gregtech.cover;

import muramasa.antimatter.cover.ICoverMode;

public enum ImportExportMode {
    EXPORT(true),
    IMPORT(false),
    EXPORT_IMPORT(true),
    IMPORT_EXPORT(false);

    boolean export;

    ImportExportMode(boolean export){
        this.export = export;
    }

    public boolean isExport() {
        return export;
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
