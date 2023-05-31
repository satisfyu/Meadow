package net.satisfyu.meadow.item.custom;

public class BowModelProvider {
    public static void registerModModels() {
        //registerBow(ObjectRegistry.HUNTING_BOW);
    }
    //TODO
    /*
    private static void registerBow(Item bow) {
        FabricModelPredicateProviderRegistry.registerArmor(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                });

        FabricModelPredicateProviderRegistry.registerArmor(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                        && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
    */
}