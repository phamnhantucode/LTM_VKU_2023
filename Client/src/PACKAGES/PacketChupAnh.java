package PACKAGES;

/**
 *
 * @author Nguyen minh tien_1601702
 */
public class PacketChupAnh extends PacketTin {
    public static final String ID = "chupanh";
    public static final String CMD_CHAPNHAN = "accept";
    public static final String CMD_KHOIDONG = "start";
    public static final String CMD_HOANTAT = "end";
    public PacketChupAnh() {
        setId(ID);
    }
}
