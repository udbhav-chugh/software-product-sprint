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
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a comment */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private List< HashMap<String, String> > comments;

  @Override
  public void init() {
    comments = new ArrayList<>();

    //Each comment map contains comment, author and Organization.
    HashMap<String, String> firstComment = new HashMap<String, String>();
    firstComment.put("Comment", "Commet 1, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    firstComment.put("Author", "Author 1");
    firstComment.put("Organization", "Organization 1");
    comments.add(firstComment);

    HashMap<String, String> secondComment = new HashMap<String, String>();
    secondComment.put("Comment", "Commet 2, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    secondComment.put("Author", "Author 2");
    secondComment.put("Organization", "Organization 2");
    comments.add(secondComment);

    HashMap<String, String> thirdComment = new HashMap<String, String>();
    thirdComment.put("Comment", "Commet 3, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    thirdComment.put("Author", "Author 3");
    thirdComment.put("Organization", "Organization 3");
    comments.add(thirdComment);

    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = convertToJson();
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  //Converts a list of HashMap<String,String> instance into a JSON string using the Gson library.
  private String convertToJson() {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }

}
