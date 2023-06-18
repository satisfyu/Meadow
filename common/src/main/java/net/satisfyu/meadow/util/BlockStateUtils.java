package net.satisfyu.meadow.util;

public class BlockStateUtils {

    public static final int NOTIFY_NEIGHBORS = (1);

    public static final int BLOCK_UPDATE = (1 << 1);

    public static final int NO_RERENDER = (1 << 2);

    public static final int RERENDER_MAIN_THREAD = (1 << 3);

    public static final int UPDATE_NEIGHBORS = (1 << 4);

    public static final int NO_NEIGHBOR_DROPS = (1 << 5);

    public static final int IS_MOVING = (1 << 6);

    public static final int DEFAULT = NOTIFY_NEIGHBORS | BLOCK_UPDATE;
    public static final int DEFAULT_AND_RERENDER = DEFAULT | RERENDER_MAIN_THREAD;


    private BlockStateUtils() {
    }
}

