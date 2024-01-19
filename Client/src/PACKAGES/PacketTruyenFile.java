package PACKAGES;

public class PacketTruyenFile extends PacketTin {
    public static final String ID = "sendfile";
    public static final String CMD_CHAPNHAN = "accept";
    public static final String CMD_KHOIDONG = "start";
    public static final String CMD_HOANTAT = "end";
    public PacketTruyenFile() {
        setId(ID);
    }
}
