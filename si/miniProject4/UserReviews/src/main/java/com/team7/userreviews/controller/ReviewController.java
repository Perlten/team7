package com.team7.userreviews.controller;

import com.team7.userreviews.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {

    static final String JDBC_DRIVER = "org.h2.Driver";

    private Connection connection;

    public ReviewController() throws Exception {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection("jdbc:h2:mem:review", "sa", "sa");

    }

    @GetMapping("/review/user/{name}")
    public List<Review> findReviewByUser(@PathVariable("name") String name) throws Exception {
        Statement st = this.connection.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM Review WHERE name = '" + name + "';");
        return getReviews(res);
    }
    @GetMapping("/review/{brand}")
    public List<Review> findReview(@PathVariable("brand") String brand) throws Exception {
        Statement st = this.connection.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM Review WHERE brand = '" + brand + "';");
        return getReviews(res);
    }

    private List<Review> getReviews(ResultSet res) throws SQLException {
        List<Review> reviewList = new ArrayList<>();
        while (res.next()) {
            Review r = new Review();
            r.brand = res.getString("brand");
            r.stars = res.getInt("stars");
            r.notes = res.getString("notes");
            r.name = res.getString("name");
            reviewList.add(r);
        }

        return reviewList;
    }
}
