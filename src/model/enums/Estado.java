package model.enums;

public enum Estado {

    /**
     * Arrumar a lógica para receber o ddd e retornar o estado
     */

    RO(69, "Ro"),
    AC(68, "ac"),
    AM(92, "am"),
    RR(95, "rr"),
    PA(91, "pa"),
    AP(96, "ap"),
    TO(63, "to"),
    MA(98, "ma"),
    PI(86, "pi"),
    CE(85, "ce"),
    RN(84, "rn"),
    PB(83, "pb"),
    PE(81, "pe"),
    AL( 82, "al"),
    SE(79, "se"),
    BA(71, "ba"),
    MG(31,"mg"),
    ES(27, "es"),
    RJ(21, "rj"),
    SP(11, "sp"),
    PR(41, "pr"),
    SC(47, "sc"),
    RS(51, "rs"),
    MS(67, "ms"),
    MT(65, "mt"),
    GO(62, "go"),
    DF(61, "df");

    private Integer ddd;
    private String uf;

    Estado(Integer ddd, String uf){
        this.ddd = ddd;
        this.uf = uf;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public static Estado pegarEstadoDoDDD(String uf){
        Estado estado = null;
        if(!uf.isEmpty()) {
            if(RO.uf.equals(uf)){
                estado = RO;
            } else if(AC.uf.equals(uf)){
                estado = AC;
            } else if(AM.uf.equals(uf)){
                estado = AM;
            } else if(RR.uf.equals(uf)){
                estado = RR;
            } else if(PA.uf.equals(uf)){
                estado = PA;
            } else if(AP.uf.equals(uf)){
                estado = AP;
            } else if(TO.uf.equals(uf)){
                estado = TO;
            } else if(MA.uf.equals(uf)){
                estado = MA;
            } else if(PI.uf.equals(uf)){
                estado = PI;
            } else if(CE.uf.equals(uf)){
                estado = CE;
            } else if(RN.uf.equals(uf)){
                estado = RN;
            } else if(PE.uf.equals(uf)){
                estado = PE;
            }else if(AL.uf.equals(uf)) {
                estado = AL;
            }
//           else if(PB.uf.equals(uf)){
//                estado = PB;
//            }else if(PB.uf.equals(uf)){
//                estado = PB;
//            }else if(PB.uf.equals(uf)){
//                estado = PB;
//            }else if(PB.uf.equals(uf)){
//                estado = PB;
//            }




        }
        return estado;
    }


}