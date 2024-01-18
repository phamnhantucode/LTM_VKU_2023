package PACKAGES;

/**
 *
 * @author Nguyen minh tien_1601702
 */
public class PacketTruyenFile extends PacketTin {
    public static final String ID = "truyenfile";
    public static final String CMD_CHAPNHAN = "chapnhan";
    public static final String CMD_KHOIDONG = "khoidong";
    public static final String CMD_HOANTAT = "hoantat";
    public PacketTruyenFile() {
        setId(ID);
    }
}
