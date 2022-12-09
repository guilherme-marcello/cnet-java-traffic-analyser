import util.FilesUtil;
import util.MenuUtils;
import util.Logger;
import trace.Capture;
import processing.*;
import java.util.Scanner;

/**
 * this class provides methods and functions to process interactively a capture and get information by applying multiple modules 
 */
public class TrafficAnalysis {
    // array of module execution options available to user
    private static String[] options = {
            "0- Exit",
            "1- Emitter-receiver pair analysis",
            "2- Total length and number of packets",
            "3- TCP ports and known services",
            "4- Number and types of ICMP packets",
            "5- Packets size analysis",
            "6- TCP connections (tries)",
            "7- TCP connections (established)",
            "8- Receiver of more traffic",
            "9- Emitter of more traffic"
    };

     /**
     * Given an args, verify the target file of the analysis, read and return a instance of Capture
     * @param args  Arguments of execution
     * @return a instance of Capture, parsed from targetFile
     */
    private static Capture prepareAnalysis(String[] args) {
        // verify if there's any target file defined by args and setup targetFile variable
        String targetFile = args.length == 0 ? "samples/traceA.csv" : args[0];
        Logger.info("Loading trace from file " + targetFile); 
        Capture capture = FilesUtil.readTrace(targetFile); // read trace and build Capture instance
        Logger.info("Finished trace parsing. Ready to go.");
        return capture; // return capture
    }

     /**
     * Given an args, execute TrafficAnalisys tool life-cycle
     * @param args  Arguments of execution
     */
    public static void main(String[] args) {
        MenuUtils.showBanner(); // display banner
        Capture capture = prepareAnalysis(args); // parse args and build capture
        Scanner stdin = new Scanner(System.in); // setup scanner
        while (true) {
            switch (MenuUtils.getUserOption(options, stdin)) { // get user option and evaluate each case
                case 0:
                    Logger.info("Thank you for using TrafficAnalysis tool! Exiting...");
                    stdin.close(); // close scanner and exit program with status 0
                    System.exit(0);
                    break;
                case 1:
                    Logger.info("Running [Emitter-receiver pair analysis]... ");
                    EmitterReceiverPair.inspectAddresses(capture);
                    break;
                case 2:
                    Logger.info("Running [Total length and number of packets]");
                    TraceTimeSize.inspectTraceTimeAndSize(capture);
                    break;
                case 3:
                    Logger.info("Running [TCP ports and known services]");
                    TCPPorts.inspectPortsAndServices(capture);
                    break;
                case 4:
                    Logger.info("Running [Number and types of ICMP packets]...");
                    ControlMessagePacket.inspectICMP(capture);
                    break;
                case 5:
                    Logger.info("Running [Packets size analysis]...");
                    PacketSize.inspectPacketsSize(capture);       
                    Logger.info("Exported additional data to ./samples/data_{DATETIME}.csv file.");
                    Logger.info("In order to generate a barplot from the exported data, execute the python script inside 'plugin' folder.");
                    break;
                case 6:
                    Logger.info("Running [TCP connections (tries)]");
                    TriedTCPConnection.inspectTriedTcpConnections(capture);
                    break;
                case 7:
                    Logger.info("Running [TCP connections (established)");
                    EstablishedTCPConnection.inspectEstablishedTcpConnections(capture);
                    break;
                case 8:
                    Logger.info("Running [Receiver of more traffic]");
                    TrafficPerIP.inspectReceivers(capture);
                    break;
                case 9:
                    Logger.info("Running [Emitter of more traffic]");
                    TrafficPerIP.inspectEmitters(capture);
                    break;
                default:
                    Logger.error("The selected option is not implemented yet.");
                    break;
            }
        }


    }
}
