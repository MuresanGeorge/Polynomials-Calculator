package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by George on 3/8/2017.
 */
public class Polinom  {
    private List<Monom> listpol= new ArrayList<>(100);

    public Polinom(List<Monom> listpol) {
        this.listpol = listpol;
    }

    public List<Monom> getListpol() {
        return listpol;
    }

    public void setListpol(List<Monom> listpol) {
        this.listpol = listpol;
    }

}
