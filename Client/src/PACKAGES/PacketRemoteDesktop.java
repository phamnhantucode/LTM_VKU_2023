package PACKAGES;

/**
 *
 * @author Nguyen minh tien_1601702
 */
public class PacketRemoteDesktop extends PacketTin {
    public static final String ID = "remotedesktop";
    public static final String CMD_CHAPNHAN = "accept";
    public static final String CMD_KHOIDONG = "start";
    public static final String CMD_HOANTAT = "end";
    public PacketRemoteDesktop() {
        setId(ID);
    }
}
