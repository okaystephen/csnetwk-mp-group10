import java.util.*;

public class ImgMessage extends Message {

    private static final String TMPDIR = System.getProperty("java.io.tmpdir");
    private String filetype;

    public ImgMessage(String sender, String filename, long timestamp) {
        super(sender, filename, timestamp);
        filetype = getFileType(filename);
    }

    public ImgMessage(String sender, String filename) {
        this(sender, filename, System.currentTimeMillis());
    }

    // gets the file extension/type of the file
    private String getFileType(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public String getFiletype() {
        return filetype;
    }
    
    public String getFilename() {
        return getTimestamp() + "." + filetype;
    }

    public String getFilepath() {
        return TMPDIR + "/" + getFilename();
    }

    public String getFilepath(boolean isSender) {
        return TMPDIR + "/" + (isSender ? "s" : "r") + getFilename();
    }

}