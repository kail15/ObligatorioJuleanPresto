/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Mozo extends Usuario {

    private List<Mesa> mesas = new ArrayList<>();

    public Mozo(String userId, String nombreUsuario, String password, String nombreCompleto) {
        super(userId, nombreUsuario, password, nombreCompleto);
    }

    public Mozo() {
        super();
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    @Override
    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    @Override
    public void agregarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    @Override
    public List<Mesa> obtenerMesas() {
        return this.getMesas();
    }

    @Override
    public List<Mesa> CambiarEstadoMesa(int id, boolean estado) {
        for (Mesa mesa : this.mesas) {
            if (id == mesa.getNumero()) {
                mesa.setEstado(estado);
                break;
            }
        }
        return this.getMesas();
    }

    @Override
    public void elimiarMesa(Mesa mesa) {
        this.mesas.removeIf(m -> (m.getNumero() == mesa.getNumero()));
    }
}
