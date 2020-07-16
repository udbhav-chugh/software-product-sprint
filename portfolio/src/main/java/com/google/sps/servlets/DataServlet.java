// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.sps.data.Comment;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private List< Comment > comments;
  
  @Override
  public void init() {
    comments = new ArrayList<>();
  }

  /* get function that returns the comments in JSON format */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = convertToJson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /*Converts a list of Comment instance into a JSON string using the Gson library.*/
  private String convertToJson(List< Comment > comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }

  /*post function to read input of the form and add the comment to List of Comment objects*/
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the input from the form.
    String comment = request.getParameter("comment");
    String author = request.getParameter("author");
    String organization = request.getParameter("organization");
    Comment newComment = new Comment(comment, author, organization);
    comments.add(newComment);
    response.sendRedirect("/index.html#comments");
  }

}
