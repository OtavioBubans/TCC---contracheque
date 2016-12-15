/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author matheus.schmitz
 */
public enum ContraChequeRoles implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return toString();
    }

    public static List<ContraChequeRoles> valuesToList() {
        return Arrays.asList(ContraChequeRoles.values());
    }
}
