package lotus.flags;

public enum Flag {

    SEMANTICO("--smt", false),
    SINTATICO("--sin", false),
    TOKENS("--tk", false);

    private String value;
    private Boolean status;

    Flag(String value, boolean status) {
        this.value = value;
        this.status = status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
