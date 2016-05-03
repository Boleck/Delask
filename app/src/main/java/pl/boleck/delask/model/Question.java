package pl.boleck.delask.model;

import android.provider.Settings;

import java.util.UUID;

/**
 * Created by Boleck on 2016-04-22.
 */
public class Question {
    public String text;
    public String device;
    public int negative;
    public int positive;

    public Question(String text, String device) {
        this.text = text;
        this.device = device;
        this.negative = 0;
        this.positive = 0;
    }

    /*
      "device": <UUID>,
  "time": <int>,
  "text": <string>,
  "positive": <int>,
  "negative": <int>
     */

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }
}
