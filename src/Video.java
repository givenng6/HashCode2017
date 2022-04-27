public class Video {
    private final int size, videoID;

    public Video(int size, int videoID){
        this.size = size;
        this.videoID = videoID;
    }

    public int getSize() {
        return size;
    }

    public int getVideoID() {
        return videoID;
    }
}
