package PACKAGES;

public class PacketTheoDoiClient extends PacketTin {
    public static final String ID = "theodoi";
    public static final String CMD_CHAPNHAN = "chapnhan";
    public static final String CMD_KHOIDONG = "khoidong";
    public static final String CMD_HOANTAT = "hoantat";
    public PacketTheoDoiClient() {
        setId(ID);
    }
}
