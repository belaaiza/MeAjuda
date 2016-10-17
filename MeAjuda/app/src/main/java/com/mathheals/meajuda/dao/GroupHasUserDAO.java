package com.mathheals.meajuda.dao;

import android.content.Context;

import java.util.List;

public class GroupHasUserDAO extends DAO {
    private static GroupHasUserDAO instance;
    Context context;

    private GroupHasUserDAO(Context currentContext) {
        super(currentContext);
        this.context = currentContext;
    }

    public static GroupHasUserDAO getInstance(final Context context) {
        if(GroupHasUserDAO.instance != null) {
            //Nothing to do
        } else {
            GroupHasUserDAO.instance = new GroupHasUserDAO(context);
        }

        return GroupHasUserDAO.instance;
    }

    public void addMember(Integer idGroup, Integer idNewMember) {
        final String QUERY;

        QUERY = "INSERT INTO Grupo_has_Usuario(Grupo_idGrupo, Usuario_idUsuario) VALUES("+ idGroup
                +", "+ idNewMember +")";

        executeQuery(QUERY);
    }

    public void addListOfMembers(Integer idGroup, List<Integer> userIdList) {
        for(int i = 0; i < userIdList.size(); i++) {
            addMember(idGroup, userIdList.get(i));
        }
    }

    public void deleteMember(Integer idGroup, Integer idMember) {
        final String QUERY;

        QUERY = "DELETE FROM Grupo_has_Usuario WHERE idGrupo = "+ idGroup +" AND idMember = " +
                ""+ idMember +"";

        executeQuery(QUERY);
    }
}
