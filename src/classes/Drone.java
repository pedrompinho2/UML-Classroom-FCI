public class Drone {
    private String id;
    private double capacidadeKg;
    private int bateriaPercent;
    private String status;

    public Drone(String id, double capacidadeKg, int bateriaPercent) {
        this.id = id;
        this.capacidadeKg = capacidadeKg;
        this.bateriaPercent = bateriaPercent;
        this.status = "DISPONIVEL";
    }
    
    public boolean checarBateria() {
        return this.bateriaPercent > 10;
    }

    public boolean disponivel(double peso) {
        return this.status.equals("DISPONIVEL") && peso <= this.capacidadeKg && checarBateria();
    }

    public void atribuirMissao() {
        this.status = "ATRIBUIDO";
    }

    public String getId() { 
        return id; 
    }

    public double getCapacidadeKg() {
        return capacidadeKg; 
        }
        
    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBateria(){
        return this.bateriaPercent;
    }

    @Override
    public String toString() {
        return "Drone{id='" + id + "', capacidadeKg=" + capacidadeKg +
               ", bateriaPercent=" + bateriaPercent +
               ", status='" + status + "'}";
    }
}