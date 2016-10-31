/**
 * File: Gamification.java
 * Purpose: Notify gamification stakeholders of gamification's variables changes
 */

package com.mathheals.meajuda.presenter;

import android.content.Context;

public interface Gamification {

    void notify(GamificationObserver gamificationObserver);
}
