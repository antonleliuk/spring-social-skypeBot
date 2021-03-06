package org.springframework.social.botframework.api.data.cards.adaptive.data.elements;

import org.springframework.social.botframework.api.data.cards.adaptive.dict.CardElementType;

import java.util.Date;

/**
 * Input.Time collects Time from the user
 * @author Anton Leliuk
 */
public class InputTime extends InputCardElement<Date, InputTime> {

    /**
     * hint of maximum value (may be ignored by some clients)
     */
    private String max;

    /**
     * hint of minimum value (may be ignored by some clients)
     */
    private String min;

    @Override
    public CardElementType getType() {
        return CardElementType.InputTime;
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }

    public InputTime max(String max){
        this.max = max;
        return this;
    }

    public InputTime min(String min){
        this.min = min;
        return this;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
