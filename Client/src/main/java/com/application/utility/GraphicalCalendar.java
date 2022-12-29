package com.application.utility;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GraphicalCalendar extends BorderPane {
    private Calendar currentMonth;

    public GraphicalCalendar() {
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        drawCalendar();
    }

    private void drawCalendar() {
        drawHeader();
        drawFooter();
        drawBody();
    }

    private void drawHeader() {
        HBox header = new HBox();
        HBox leftSpacer = new HBox();
        HBox rightSpacer = new HBox();
        HBox.setHgrow(leftSpacer, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, javafx.scene.layout.Priority.ALWAYS);
        String monthString = getMonthName(currentMonth.get(Calendar.MONTH));
        String yearString = String.valueOf(currentMonth.get(Calendar.YEAR));
        Label tHeader = new Label(monthString + ", " + yearString);
        tHeader.setFont(Font.font("System", FontWeight.BOLD, 15));
        // setFont weight to bold
        Button btPrev = new Button();
        Button btNext = new Button();
        header.getChildren().addAll(btPrev, leftSpacer, tHeader, rightSpacer, btNext);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15, 20, 0, 20));


        // Buttons CSS
        btPrev.setStyle("-fx-background-color: transparent;");
        btNext.setStyle("-fx-background-color: transparent;");

        Image previous = new Image("file:Client/src/main/resources/icons/previous-arrow.png");
        Image next = new Image("file:Client/src/main/resources/icons/next-arrow.png");

        ImageView previousIco = new ImageView(previous);
        ImageView nextIco = new ImageView(next);

        previousIco.setFitHeight(20);
        previousIco.setFitWidth(20);
        nextIco.setFitHeight(20);
        nextIco.setFitWidth(20);

        btPrev.setGraphic(previousIco);
        btNext.setGraphic(nextIco);

        setTop(header);
        setAlignment(tHeader, Pos.CENTER);
        setMargin(tHeader, new Insets(0, 0, 15, 0));



        btPrev.setOnAction(e -> previous());
        btNext.setOnAction(e -> next());
    }

    private void drawBody() {
        GridPane gpBody = new GridPane();
        gpBody.setHgap(10);
        gpBody.setVgap(10);
        gpBody.setAlignment(Pos.CENTER);
        //gpBody.setMinHeight(300);

        // Draw days of the week
        for (int day = 1; day <= 7; day++) {
            Text tDayName = new Text(getDayName(day));
            gpBody.add(tDayName, day - 1, 0);
        }

        // Draw days in month
        int currentDay = currentMonth.get(Calendar.DAY_OF_MONTH);
        int daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        int row = 1;
        for (int i = currentDay; i <= daysInMonth; i++) {
            if (dayOfWeek == 8) {
                dayOfWeek = 1;
                row++;
            }
            Text tDate = new Text(String.valueOf(currentDay));
            if (currentDay == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    currentMonth.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) &&
                    currentMonth.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                tDate.setFill(Color.RED);
            }
            gpBody.add(tDate, dayOfWeek - 1, row);
            currentDay++;
            dayOfWeek++;
        }

        // Draw previous month days
        dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        if (currentDay != 1) {
            Calendar prevMonth = getPreviousMonth(currentMonth);
            int daysInPrevMonth = prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = dayOfWeek - 2; i >= 0; i--) {
                Text tDate = new Text(String.valueOf(daysInPrevMonth));
                tDate.setFill(Color.GRAY);
                gpBody.add(tDate, i, 1);
                daysInPrevMonth--;
            }
        }

        // Draw next month days
        currentMonth.set(Calendar.DAY_OF_MONTH, currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != 7) {
            int day = 1;
            for (int i = dayOfWeek; i < 7; i++) {
                Text tDate = new Text(String.valueOf(day));
                tDate.setFill(Color.GRAY);
                gpBody.add(tDate, i, row);
                day++;
            }
        }

        setCenter(gpBody);
        setMargin(gpBody, new Insets(20));
    }

    private void drawFooter() {

    }

    private void previous() {
        getChildren().clear();
        currentMonth = getPreviousMonth(currentMonth);
        drawCalendar();
    }

    private void next() {
        getChildren().clear();
        currentMonth = getNextMonth(currentMonth);
        drawCalendar();
    }

    private GregorianCalendar getPreviousMonth(Calendar cal) {
        int cMonth = cal.get(Calendar.MONTH);
        int pMonth = cMonth == 0 ? 11 : cMonth - 1;
        int pYear = cMonth == 0 ? cal.get(Calendar.YEAR) - 1 : cal.get(Calendar.YEAR);
        return new GregorianCalendar(pYear, pMonth, 1);
    }

    private GregorianCalendar getNextMonth(Calendar cal) {
        int cMonth = cal.get(Calendar.MONTH);
        int pMonth = cMonth == 11 ? 0 : cMonth + 1;
        int pYear = cMonth == 11 ? cal.get(Calendar.YEAR) + 1 : cal.get(Calendar.YEAR);
        return new GregorianCalendar(pYear, pMonth, 1);
    }

    private String getDayName(int n) {
        StringBuilder sb = new StringBuilder();
        switch (n) {
            case 1 -> sb.append("Sun");
            case 2 -> sb.append("Mon");
            case 3 -> sb.append("Tue");
            case 4 -> sb.append("Wed");
            case 5 -> sb.append("Thu");
            case 6 -> sb.append("Fri");
            case 7 -> sb.append("Sat");
        }
        return sb.toString();
    }

    private String getMonthName(int n) {
        String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return monthNames[n];
    }
}