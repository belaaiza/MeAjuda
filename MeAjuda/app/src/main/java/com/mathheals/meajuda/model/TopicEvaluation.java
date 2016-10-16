/**
 * file:TopicEvaluation.java
 * purpose: creating the model to evaluate a topic
 */
package com.mathheals.meajuda.model;

import com.mathheals.meajuda.exception.TopicEvaluationException;

public class TopicEvaluation {

    private Float rating;
    private Integer userId;
    private Integer topicId;
    public static final String EVALUATION_IS_INVALID = "A avaliação deve estar entre 0 e 5";
    public static final String USER_ID_IS_INVALID = "O identificador do usuário está inválido";
    public static final String TOPIC_ID_IS_INVALID = "O identificador do post está inválido";

    public TopicEvaluation(Float rating, Integer userId, Integer topicId) throws TopicEvaluationException {
        setRating(rating);
        setUserId(userId);
        setTopicId(topicId);
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) throws TopicEvaluationException {
        if(rating>=0f && rating<=5f) {
            this.rating = rating;
        }
        else{
            throw new TopicEvaluationException(EVALUATION_IS_INVALID);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) throws TopicEvaluationException {
        if(userId <= Integer.MAX_VALUE && userId >= 1) {
            this.userId = userId;
        }
        else{
            throw new TopicEvaluationException(USER_ID_IS_INVALID);
        }
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer eventId) throws TopicEvaluationException {
        if(eventId <= Integer.MAX_VALUE && eventId >= 1) {
            this.topicId = eventId;
        }
        else{
            throw new TopicEvaluationException(TOPIC_ID_IS_INVALID);
        }
    }

}
