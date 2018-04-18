package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    @FXML
    TextField pol1Text;
    @FXML
    TextField pol2Text;
    @FXML
    TextField rezText;

    public List<Monom> citirePolinom(String p1) {
        List<Monom> listpol = new ArrayList<>();
        String c = "";
        String p = "";
        Integer i = 0;
        while (i < p1.length()) {
            p = "";
            c = "";
            if (p1.substring(i, i + 1).equals("-")) {
                c = "-";
                i++;
            } else if (p1.substring(i, i + 1).equals("+"))
                i++;
            while ("0123456789".contains(p1.substring(i, i + 1))) {
                c = c + p1.substring(i, i + 1);
                i++;
                if (i == p1.length())
                    break;
            }
            if (i < p1.length()) {
                if (p1.substring(i, i + 1).equals("*"))
                    i++;
            }
            if (i < p1.length()) {
                if (p1.substring(i, i + 1).equals("x"))
                    i++;
                else
                    p = "0";
            } else
                p = "0";
            if (i < p1.length()) {
                if (p1.substring(i, i + 1).equals("^")) {
                    i++;
                    while ("0123456789".contains(p1.substring(i, i + 1))) {
                        p = p + p1.substring(i, i + 1);
                        i++;
                        if (i == p1.length())
                            break;
                    }
                }
            }
            if (c == "")
                c = "1";
            if (c == "-")
                c += "1";
            if (p == "")
                p = "1";


            Monom m = new Monom(c, p);
            listpol.add(m);
        }
        return listpol;
    }//


    public void adunarePolinoame() {
        List<Monom> listpol1 = citirePolinom(pol1Text.getText());
        List<Monom> listpol2 = citirePolinom(pol2Text.getText());

        Polinom pol1 = new Polinom(listpol1);
        Polinom pol2 = new Polinom(listpol2);

        for (Monom monom : pol1.getListpol()) {
            for (Monom monom1 : pol2.getListpol()) {
                if (monom.getPutere().equals(monom1.getPutere())) {
                    Integer c1;
                    Integer c2;
                    c1 = Integer.parseInt(monom.getCoef());
                    c2 = Integer.parseInt(monom1.getCoef());
                    c1 = c1 + c2;
                    monom.setCoef(String.valueOf(c1));
                }
            }
        }
        //dupa ce am facut adunarea si mi s-au schimbat coef din monom, parcurg iarasi polinomul2 si vad daca nu am monoame cu aceeasi putere si in polinomul 1 ,iar daca nu am adun monomul din polinomul2 la lista de monoame in polinomul 1 unde mi se face rezultatul
        for (Monom monom : pol2.getListpol()) {
            Integer gasit = 0;
            for (Monom monom1 : pol1.getListpol()) {
                if (monom.getPutere().equals(monom1.getPutere())) {
                    gasit = 1;
                    break;
                }
            }
            if (gasit == 0) {
                pol1.getListpol().add(monom);
            }
        }
        pol1.setListpol(sort(pol1.getListpol()));
        String rez = transformareRez(pol1.getListpol());
        rezText.setText(rez);

    }//


    private List<Monom> sort(List<Monom> listpol) {
        Integer i;
        Integer j;
        for (i = 0; i < listpol.size() - 1; i++) {
            for (j = i + 1; j < listpol.size(); j++) {
                Integer m1 = Integer.parseInt(listpol.get(i).getPutere());
                Integer m2 = Integer.parseInt(listpol.get(j).getPutere());

                if (m1 < m2) {
                    Monom m = listpol.get(i);
                    listpol.set(i, listpol.get(j));
                    listpol.set(j, m);


                }
            }
        }
        return listpol;
    }//

    public String transformareRez(List<Monom> listapol) {
        Integer i;
        String rez = "";
        for (i = 0; i < listapol.size(); i++) {
            if (!listapol.get(i).getCoef().equals("0")) {

                rez += listapol.get(i).getCoef();

                if (!listapol.get(i).getPutere().equals("0")) {
                    if (listapol.get(i).getCoef().equals("1"))
                        rez += "x";
                    else
                        rez += "*x";
                    if (!listapol.get(i).getPutere().equals("1"))
                        rez += "^" + listapol.get(i).getPutere();

                }
                if (i < listapol.size() - 1) {
                    if (!listapol.get(i + 1).getCoef().substring(0, 1).equals("-"))
                        rez += "+";
                }
            } else
                rez += "0"; //elsul asta e ca atunci cand derivez 6 sa-mi scrie 0
        }

        return rez;
    }//

    public void scaderePolinoame() {
        List<Monom> listpol1 = citirePolinom(pol1Text.getText());
        List<Monom> listpol2 = citirePolinom(pol2Text.getText());

        Polinom pol1 = new Polinom(listpol1);
        Polinom pol2 = new Polinom(listpol2);

        for (Monom monom : pol1.getListpol()) {
            for (Monom monom1 : pol2.getListpol()) {
                if (monom.getPutere().equals(monom1.getPutere())) {
                    Integer c1;
                    Integer c2;
                    c1 = Integer.parseInt(monom.getCoef());
                    c2 = Integer.parseInt(monom1.getCoef());
                    c1 = c1 - c2;
                    monom.setCoef(String.valueOf(c1));
                }
            }
        }
        for (Monom monom : pol2.getListpol()) {
            Integer gasit = 0;
            for (Monom monom1 : pol1.getListpol()) {
                if (monom.getPutere().equals(monom1.getPutere())) {
                    gasit = 1;
                    break;
                }
            }
            if (gasit == 0) {
                if (!monom.getCoef().substring(0, 1).equals("-"))
                    monom.setCoef("-" + monom.getCoef());
                else
                    monom.setCoef(monom.getCoef().substring(1));
                pol1.getListpol().add(monom);
                // if(monom.getCoef().substring(0,1).equals("-"))
                //monom.setCoef("");
            }
            //if(monom.getCoef().substring(0,1).equals("-"))
            // monom.setCoef("");
        }
        pol1.setListpol(sort(pol1.getListpol()));
        String rez = transformareRez(pol1.getListpol());
        rezText.setText(rez);

    }//


    public void derivarePolinom1() {
        List<Monom> listapol1 = citirePolinom(pol1Text.getText());
        Polinom pol1 = new Polinom(listapol1);

        for (Monom monom1 : pol1.getListpol()) {
            //System.out.println(pol1.getListpol());
            Integer c1;
            Integer p1;
            c1 = Integer.parseInt(monom1.getCoef());
            p1 = Integer.parseInt(monom1.getPutere());
            c1 *= p1;
            p1 -= 1;


            monom1.setCoef(String.valueOf(c1));
            monom1.setPutere(String.valueOf(p1));
            //System.out.println(pol1.getListpol());

        }
        String rez = transformareRez(pol1.getListpol());
        rezText.setText(rez);
    }//

    public void inmultirePolinoame()    {
        List<Monom> listapol1 = citirePolinom(pol1Text.getText());
        List<Monom> listapol2 = citirePolinom(pol2Text.getText());
        List<Monom> listapol3 = new ArrayList<>(100);


        Polinom pol1 = new Polinom(listapol1);
        Polinom pol2 = new Polinom(listapol2);
        Polinom pol3 = new Polinom(listapol3);

        for (Monom monom1 : pol1.getListpol()) {
            for (Monom monom2 : pol2.getListpol()) {
                Integer c1, c2, c3;
                Integer p1, p2, p3;
                c1 = Integer.parseInt(monom1.getCoef());
                c2 = Integer.parseInt(monom2.getCoef());
                c3 = c1 * c2;
                p1 = Integer.parseInt(monom1.getPutere());
                p2 = Integer.parseInt(monom2.getPutere());
                p3 = p1 + p2;
                Monom monom3 = new Monom();
                monom3.setCoef(String.valueOf(c3));
                monom3.setPutere(String.valueOf(p3));


                //System.out.println(monom3.getCoef());
                //System.out.println(monom3.getPutere());
                //System.out.println();


                pol3.getListpol().add(monom3);
               transInmultire(pol3);
               // pol3.getListpol().forEach(e->System.out.println(e.toString()));

            }
        }

        pol3.setListpol(sort(pol3.getListpol()));
        String rez = transformareRez(pol3.getListpol());
        rezText.setText(rez);
    }//


    private Polinom transInmultire(Polinom p){
        //p.getListpol().forEach(e->System.out.println(e.toString()));
        Integer i;
        for(i=0;i<p.getListpol().size();i++){
            Integer j = i + 1;
            if(j<p.getListpol().size()){
                for(j=i+1;j<p.getListpol().size();j++){
                    if(p.getListpol().get(i).getPutere().equals(p.getListpol().get(j).getPutere())){
                        Integer c1 = Integer.parseInt(p.getListpol().get(i).getCoef());
                        Integer c2 = Integer.parseInt(p.getListpol().get(j).getCoef());
                        c1 = c1+c2;
                        p.getListpol().get(i).setCoef(String.valueOf(c1));
                        p.getListpol().remove(p.getListpol().get(j));
                        j--;
                    }
                }
            }
        }
        return p;
    }//
}



