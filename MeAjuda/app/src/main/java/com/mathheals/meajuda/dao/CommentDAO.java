package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.content.Context;

/**
 * Created by geovanni on 02/10/16.
 */
public class CommentDAO extends DAO {

    private static CommentDAO instance;

    private CommentDAO(Context currentContext) {
        super((Activity) currentContext);
    }

    public static CommentDAO getInstance(final Context context) {
        if(CommentDAO.instance == null) {

            CommentDAO.instance = new CommentDAO(context);

        } else {
            //nothing to do
        }

        return CommentDAO.instance;
    }
    public void createComment(final int idPai,final int Topic_idTopic,final int Topic_Category_idCategory)
    {

        String QUERY = "INSERT INTO `1233593`.`Comentario` (`idComentario`, `idPai`, `Topico_idTopico`,"+
                                                     "`Topico_Categoria_idCategoria`) VALUES (NULL,"+
                                                     " "+ idPai +" \", "+ Topic_idTopic +", \" "+ Topic_Category_idCategory +
                                                     " \")";

        executeQuery(QUERY);
    }
}
