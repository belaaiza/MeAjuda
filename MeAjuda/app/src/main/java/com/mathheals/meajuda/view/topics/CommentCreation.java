package com.mathheals.meajuda.view.topics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.CommentDAO;
import com.mathheals.meajuda.presenter.CommentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentCreation extends Fragment implements View.OnClickListener{
    Integer idTopic = 0;
    Integer idCategory = 0;

    EditText commentField;

    public CommentCreation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment_creation, container, false);

        Bundle args = this.getArguments();

        idTopic = args.getInt("idTopic", 0);
        idCategory = args.getInt("idCategory", 0);

        assert idTopic != 0;
        assert idCategory != 0;

        ImageView createCommenButton = (ImageView) view.findViewById(R.id.create_comment_button);
        createCommenButton.setOnClickListener(this);

        commentField = (EditText) view.findViewById(R.id.descriptionCommentField);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_comment_button:
                String description = commentField.getText().toString();

                CommentPresenter commentPresenter = CommentPresenter.getInstance(getContext());
                commentPresenter.createComment(idTopic, idCategory, description);

                Log.d("id do topico", idTopic + "");
                Log.d("id da categoria", idCategory + "");
                Log.d("comentário", description);

                Toast.makeText(getActivity().getBaseContext(), "Comentário criado com sucesso",
                        Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
