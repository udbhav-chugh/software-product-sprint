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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.sps.data.Comment;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/data-get")
public class DataGetServlet extends HttpServlet {

  private static DatastoreService datastore;
  private static Query query;
  @Override
  public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
    query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
  }
  /* get function that returns the comments in JSON format */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    PreparedQuery results = datastore.prepare(query);
    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String comment = (String) entity.getProperty("comment");
      String author = (String) entity.getProperty("author");
      String organization = (String) entity.getProperty("organization");
      long timestamp = (long) entity.getProperty("timestamp");
      String imageUrl = (String) entity.getProperty("imageUrl");

      Comment finalComment = new Comment(comment, author, organization, timestamp, imageUrl);
      comments.add(finalComment);
    }
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

}
