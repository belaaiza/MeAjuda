package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.model.Group;

public class GroupDAO extends DAO{
    private static GroupDAO instance;
    Context context;

    private GroupDAO(Context currentContext) {
        super(currentContext);
        this.context = currentContext;
    }

    public static GroupDAO getInstance(final Context context) {
        if(GroupDAO.instance != null) {
            //Nothing to do
        } else {
            GroupDAO.instance = new GroupDAO(context);
        }

        return GroupDAO.instance;
    }

    public void createGroup(Group group) {
        final String QUERY;

        QUERY = "INSERT INTO Grupo(idProprietario, nome) VALUES (\"" + group.getIdOwner() +
                "\", \"" + group.getName() + "\")";

        executeQuery(QUERY);
    }

    public void deleteGroup(Integer idGroup) {
        final String QUERY;

        QUERY = "DELETE FROM Grupo WHERE idGrupo = "+ idGroup +" ";

        executeQuery(QUERY);
    }

    public void changeOwner(Integer idGroup, Integer idNewOwner) {
        final String QUERY;

        QUERY = "UPDATE Grupo SET idProprietario = "+ idNewOwner +" WHERE idGrupo = "+ idGroup +"";

        executeQuery(QUERY);
    }
}
