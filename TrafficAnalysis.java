import util.FilesUtil;
import trace.Capture;
import processing.PacketSize;

public class TrafficAnalysis {
    private static void loadBanner() {
        System.out.println("___  __        ___  ___    __                              __     __  ");
        System.out.println(" |  |__)  /\\  |__  |__  | /  `     /\\  |\\ |  /\\  |    \\ / /__` | /__` ");
        System.out.println(" |  |  \\ /~~\\ |    |    | \\__,    /~~\\ | \\| /~~\\ |___  |  .__/ | .__/ ");                                                                        
    }

    public static void main(String[] args) {
        loadBanner();
        Capture capture = FilesUtil.readTrace("samples/traceA.csv");
        System.out.println(PacketSize.inspectPacketsSize(capture));
    }
}