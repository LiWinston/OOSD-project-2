import bagel.*;
public class ShowMessage {
    private int X;
    private int Y;
    private String messageDetail;
    private final Font ft;
    final static int SPECIFIC_FONTSIZE = 64;


    public ShowMessage(String messageDetail,int X,int Y) {
        this.messageDetail = messageDetail;
        this.X = X;
        this.Y = Y;
        ft = new Font("res/FSO8BITR.TTF",SPECIFIC_FONTSIZE);
    }

    public ShowMessage(String messageDetail,int X,int Y, int fontSize) {
        this.X = X;
        this.Y = Y;
        this.messageDetail = messageDetail;
        this.ft = new Font("res/FSO8BITR.TTF",fontSize);
    }

    public void Show(){
        ft.drawString(messageDetail, X, Y);
    }
    public void Show(DrawOptions drop){
        ft.drawString(messageDetail, X, Y, drop);
    }

}
