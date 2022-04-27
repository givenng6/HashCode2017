import java.util.ArrayList;

public class Cache {
    private final int cacheID;
    private final int MAX_MB;
    private int freeSpace;
    private final ArrayList<Video> storedVideos;

    public Cache(int cacheID, int MAX_MB){
        this.cacheID = cacheID;
        this.MAX_MB = MAX_MB;
        storedVideos = new ArrayList<>();
        freeSpace = MAX_MB;
    }

    public int availableSpace(){
        return this.freeSpace;
    }

    public boolean isEmpty(){
        return storedVideos.size() == 0;
    }

    public void storeVideo(Video video){
        storedVideos.add(video);
        freeSpace = freeSpace - video.getSize();
    }

    public int getCacheID(){
        return this.cacheID;
    }

    public ArrayList<Video> getStoredVideos(){
        return storedVideos;
    }
}
