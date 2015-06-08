/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

public enum TypeOfFunction {
    SIN,
    COS,
    TG,
    CTG,
    ARCSIN,
    ARCCOS,
    ARCTG,
    ARCCTG,
    LN;
    public static TypeOfFunction getType(int index){
        switch(index){
            case 0:
                return SIN;
            case 1:
                return COS;
            case 2:
                return TG;
            case 3:
                return CTG;
            case 4:
                return ARCSIN;
            case 5:
                return ARCCOS;
            case 6:
                return ARCTG;
            case 7:
                return ARCCTG;
            case 8:
                return LN;
        }
        return null;
    }

}
