package kr.or.ddit.board.vo;

public class ReplyVO {
    private int RENUM;
    private String REDATE;
    private String NAME;
    private String CONT;

    public int getBONUM() {
        return BONUM;
    }

    public void setBONUM(int BONUM) {
        this.BONUM = BONUM;
    }

    public String getCONT() {
        return CONT;
    }

    public void setCONT(String CONT) {
        this.CONT = CONT;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getREDATE() {
        return REDATE;
    }

    public void setREDATE(String REDATE) {
        this.REDATE = REDATE;
    }

    public int getRENUM() {
        return RENUM;
    }

    public void setRENUM(int RENUM) {
        this.RENUM = RENUM;
    }

    private int BONUM;
}
