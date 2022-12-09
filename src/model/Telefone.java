package model;

public class Telefone {
  
    private String ddd;
    private String numeroTelefone;

    public Telefone() {
    }

    public Telefone(String ddd, String numeroTelefone) {
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
    }

    // parsing phone numbers
    public Telefone(String telefoneCompleto) {
        String[] linha = telefoneCompleto.split(" ");
        this.ddd = linha[0];
        this.numeroTelefone = linha[1];
    }

    public String getDDD(){
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone){
        this.numeroTelefone = numeroTelefone;
    }

  @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
        result = prime * result + ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        if (ddd == null) {
            if (other.ddd != null)
                return false;
        } else if (!ddd.equals(other.ddd))
            return false;
        if (numeroTelefone == null) {
            if (other.numeroTelefone != null)
                return false;
        } else if (!numeroTelefone.equals(other.numeroTelefone))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "(" + ddd + ") " + numeroTelefone;
    }

  

}
