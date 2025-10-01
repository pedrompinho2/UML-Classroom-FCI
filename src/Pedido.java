package com.drones.domain;

import java.time.LocalDateTime;

public class Pedido {
    private String id; 
    private double pesoKg;
    private Endereco destino;
    private String status;
    private LocalDateTime criadoEm;
    private LocalDateTime entregueEm;
    private Drone droneAtribuido;
    private String clienteId; +

    public Pedido(String clienteId, double pesoKg, Endereco destino)
    {
        this.id = String.valueOf(System.currentTimeMillis()); 
        this.clienteId = clienteId;
        this.pesoKg = pesoKg;
        this.destino = destino;
        this.status = "CRIADO";
        this.criadoEm = LocalDateTime.now();
    }

    public String getId()
    {
        return id;
    }
    
    public String getClienteId()
    {
        return clienteId;
    }

    public double getPesoKg()
    {
        return pesoKg;
    }
    
    public void setDroneAtribuido(Drone drone)
    {
        this.droneAtribuido = drone;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
}
