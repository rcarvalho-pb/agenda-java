package model;

public enum Estado {

    /**
     * Arrumar a lógica para receber o ddd e retornar o estado
     */

    RO(69, "ro"),
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

    public static Estado pegarDdd(String uf){
        Estado estado = null;
        if(!uf.isEmpty()) {
            if(RO.uf.equals(uf)){
                estado = RO;
            } else if(AC.uf.equals(uf)){
                estado = AC;
            } else if(AM.uf.equals(uf)){
                estado = AM;
//            } else if(RR.uf.equals(uf)){
//                uf = RR;
//            } else if(PA.uf.equals(uf)){
//                uf = PA;
//            } else if(AP.uf.equals(uf)){
//                uf = AP;
//            } else if(TO.uf.equals(uf)){
//                uf = TO;
//            } else if(MA.uf.equals(uf)){
//                uf = MA;
//            } else if(PI.uf.equals(uf)){
//                uf = PI;
//            } else if(CE.uf.equals(uf)){
//                uf = CE;
//            } else if(RN.uf.equals(uf)){
//                uf = RN;
//            } else if(PB.uf.equals(uf)){
//                uf = PB;
            }
        }
        return estado;
    }

}