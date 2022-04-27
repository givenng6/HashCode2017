public class Node {
    private final int cacheID, latency;

    public Node(int cacheID, int latency){
        this.cacheID = cacheID;
        this.latency = latency;
    }

    public int getLatency() {
        return latency;
    }

    public int getCacheID() {
        return cacheID;
    }
}
