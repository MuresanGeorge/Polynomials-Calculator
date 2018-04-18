package sample;

/**
 * Created by George on 3/8/2017.
 */
public class Monom {
    private String coef;
    private String putere;

    public Monom(String coef, String putere) {
        this.coef = coef;
        this.putere = putere;
    }
    public Monom() {
        this.coef = coef;
        this.putere = putere;
    }

    public String getCoef() {
        return coef;
    }

    public void setCoef(String coef) {
        this.coef = coef;
    }

    public String getPutere() {
        return putere;
    }

    public void setPutere(String putere) {
        this.putere = putere;
    }

    @Override
    public String toString() {
        return "Monom{" +
                "coef='" + coef + '\'' +
                ", putere='" + putere + '\'' +
                '}';
    }
}