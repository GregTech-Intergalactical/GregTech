package muramasa.gti.items;

import muramasa.antimatter.item.ItemBasic;

public class ItemIntCircuit extends ItemBasic<ItemIntCircuit> {

    public final int circuitId;

    public ItemIntCircuit(String domain, String id, int circuitId, Properties properties) {
        super(domain, id, properties);
        this.circuitId = circuitId;
    }

    public ItemIntCircuit(String domain, String id,int circuitId) {
        super(domain, id);
        this.circuitId = circuitId;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
