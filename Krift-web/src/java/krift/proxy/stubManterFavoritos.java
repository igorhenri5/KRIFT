/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.proxy;

import krift.common.model.services.IManterFavoritos;

/**
 *
 * @author Igor
 */
public class stubManterFavoritos implements IManterFavoritos{

    @Override
    public boolean favoritar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desfavoritar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
