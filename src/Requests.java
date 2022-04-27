public class Requests {

    private final int endpointID;
    private int RequestSize;
    private Video video;

    public Requests(int endpointID, int RequestSize, Video video){
        this.RequestSize = RequestSize;
        this.endpointID = endpointID;
        this.video = video;
    }

    public int getRequestSize() {
        return RequestSize;
    }

    public Video getVideo() {
        return video;
    }

    public int getEndpointID() {
        return endpointID;
    }
}
