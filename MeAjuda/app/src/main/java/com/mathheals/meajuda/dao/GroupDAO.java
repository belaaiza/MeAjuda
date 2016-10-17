package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;

import com.mathheals.meajuda.model.Group;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Integer createGroup(Group group) {
        final String QUERY_INSERT, QUERY_CONSULT, QUERY_UPDATE;

        QUERY_INSERT = "INSERT INTO Grupo(idProprietario, nome) VALUES (\"" + group.getIdOwner() +
                "\", \"" + group.getName() + "\");";

        executeQuery(QUERY_INSERT);

        QUERY_CONSULT = "SELECT idGrupo FROM Grupo WHERE " +
                "idProprietario = "+ group.getIdOwner() +" " +
                "AND nome = \""+ group.getName() +"\" AND flag = 0";


        JSONObject idGroupJSONObject = executeConsult(QUERY_CONSULT);

        Integer idGroup = null;
        try {
            idGroup = idGroupJSONObject.getJSONObject("0").getInt("idGrupo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        QUERY_UPDATE = "UPDATE Grupo SET flag = 1 WHERE idGrupo = "+ idGroup +" ";

        executeQuery(QUERY_UPDATE);

        return idGroup;
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
