package com.mathheals.meajuda.dao;

import android.content.Context;

public class GroupHasUsuarioDAO extends DAO {
    private static GroupHasUsuarioDAO instance;
    Context context;

    private GroupHasUsuarioDAO(Context currentContext) {
        super(currentContext);
        this.context = currentContext;
    }

    public static GroupHasUsuarioDAO getInstance(final Context context) {
        if(GroupHasUsuarioDAO.instance != null) {
            //Nothing to do
        } else {
            GroupHasUsuarioDAO.instance = new GroupHasUsuarioDAO(context);
        }

        return GroupHasUsuarioDAO.instance;
    }

    public void addMember(Integer idGroup, Integer idNewMember) {
        final String QUERY;

        QUERY = "INSERT INTO Grupo_has_Usuario(Grupo_idGrupo, Usuario_idUsuario) VALUES("+ idGroup
                +", "+ idNewMember +")";

        executeQuery(QUERY);
    }

    public void deleteMember(Integer idGroup, Integer idMember) {
        final String QUERY;

        QUERY = "DELETE FROM Grupo_has_Usuario WHERE idGrupo = "+ idGroup +" AND idMember = " +
                ""+ idMember +"";

        executeQuery(QUERY);
    }
}
