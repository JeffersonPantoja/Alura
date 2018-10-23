package br.com.alura.aluraviagens.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;

public class Pacote implements Parcelable{
    private final String local;
    private final String imagem;
    private final int dias;
    private double preco;


    public Pacote(String local, String imagem, int dias, double preco) {
        this.local = local;
        this.imagem = imagem;
        this.dias = dias;
        this.preco = preco;
    }

    protected Pacote(Parcel in) {
        local = in.readString();
        imagem = in.readString();
        dias = in.readInt();
        preco = in.readDouble();
    }

    public static final Creator<Pacote> CREATOR = new Creator<Pacote>() {
        @Override
        public Pacote createFromParcel(Parcel in) {
            return new Pacote(in);
        }

        @Override
        public Pacote[] newArray(int size) {
            return new Pacote[size];
        }
    };

    public String getLocal() {
        return local;
    }

    public String getImagem() {
        return imagem;
    }

    public int getDias() {
        return dias;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(local);
        dest.writeString(imagem);
        dest.writeInt(dias);
        dest.writeDouble(preco);
    }
}
