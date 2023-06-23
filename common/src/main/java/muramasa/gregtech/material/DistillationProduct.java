package muramasa.gregtech.material;

import java.util.List;

public record DistillationProduct(int inputAmount, int distillerInputAmount, List<FluidProduct> outputs, int distilleryTicks, int ticks, int euPerTick) {
}
