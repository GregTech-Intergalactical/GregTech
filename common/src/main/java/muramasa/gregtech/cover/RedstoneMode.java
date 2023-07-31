package muramasa.gregtech.cover;

import muramasa.antimatter.cover.ICoverMode;

public enum RedstoneMode implements ICoverMode {
    NORMAL("Normal", 60, 33),
    INVERTED("Inverted", 78, 33),
    NO_WORK("No Work at all", 96, 33);
    int x, y;
    String name;

    RedstoneMode(String name, int x, int y){
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
