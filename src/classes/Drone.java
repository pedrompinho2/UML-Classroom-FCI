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

    // Verifica se o Drone tem bateria suficiente
    
    public boolean checarBateria() {
        return this.bateriaPercent > 10;
    }

    //Verifica se o Drone está disponível e pode suportar o peso da carga
    public boolean disponivel(double peso) {
        return this.status.equals("DISPONIVEL") && peso <= this.capacidadeKg;
    }

    //Atribui uma missão ao drone alterando o status para ATRIBUIDO

    public void atribuirMissao() {
        this.status = "ATRIBUIDO";
        System.out.println("Drone " + this.id + " -> Status: ATRIBUIDO.");
    }

    // Getters
    public String getId() { return id; }
    public double getCapacidadeKg() { return capacidadeKg; }
    public String getStatus() { return status; }
}