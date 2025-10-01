package src;

public class Drone
{
    private String id;
    private double capacidadeKg;
    private int bateriaPercent;
    private String status;

    public Drone(String id, double capacidadeKg, int bateriaPercent)
    {
        this.id = id;
        this.capacidadeKg = capacidadeKg;
        this.bateriaPercent = bateriaPercent;
        this.status = "Disponivel";
    }

    public boolean checarBateria()
    {
        return this.bateriaPercent > 20;
    }

    public boolean disponivel(double pesoKg)
    {
        boolean temCapacidade = pesoKg <= this.capacidadeKg;
        boolean estaDisponivel = this.status.equals("Disponivel") && checarBateria();

        return temCapacidade && estaDisponivel;
    }

    public String getId()
    {
        return id;
    }

    public double getCapacidadeKg()
    {
        return capacidadeKg;
    }

    public int getBateriaPercent()
    {
        return bateriaPercent;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
