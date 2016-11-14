/**
 * file:TopicEvaluation.java
 * purpose: creating the model to evaluate a topic
 */
package com.mathheals.meajuda.model;

import com.mathheals.meajuda.exception.TopicEvaluationException;

public class TopicEvaluation {

    private Integer idEvaluation;
    private Integer evaluationDescription;
    private Integer idTopic;
    private Integer idCategory;
    private Integer idUser;

    public static final String USER_ID_IS_INVALID = "O identificador do usuário está inválido";
    public static final String TOPIC_ID_IS_INVALID = "O identificador do post está inválido";

    public TopicEvaluation(Integer idEvaluation, Integer evaluationDescription, Integer idTopic,
                           Integer idCategory, Integer idUser) throws TopicEvaluationException {
        setIdEvaluation(idEvaluation);
        setEvaluationDescription(evaluationDescription);
        setIdTopic(idTopic);
        setIdCategory(idCategory);
        setIdUser(idUser);
    }

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    private void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Integer getEvaluationDescription() {
        return evaluationDescription;
    }

    private void setEvaluationDescription(Integer evaluationDescription) {
        this.evaluationDescription = evaluationDescription;
    }

    public Integer getIdTopic() {
        return idTopic;
    }

    private void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    private void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getIdUser() {
        return idUser;
    }

    private void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
