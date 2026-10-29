package edu.fpt.de201056.ch08.b3;

// TODO 1: Model & "database" trong bộ nhớ

import java.time.LocalDate;
import java.util.List;

public record User(String fullName, String email, String username, String password,
                   String gender, LocalDate dob, String major, List<String> hobbies) {}
