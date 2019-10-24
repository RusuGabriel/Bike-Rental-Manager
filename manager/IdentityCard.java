package manager;

public class IdentityCard {
    private String cnp;
    private char sex;
    private String series;
    private Integer number;

    public IdentityCard(String cnp,char sex, String series, Integer number)
    {
        this.series=series;
        this.cnp=cnp;
        this.number =number;
        this.sex=sex;
    }

    public IdentityCard() {

    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setCNP(String cnp) {
        this.cnp = cnp;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public String getCNP() {
        return cnp;
    }

    public String getSeries() {
        return series;
    }
}
