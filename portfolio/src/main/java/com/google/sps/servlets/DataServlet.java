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

//POJO for Comment.
class Comment{
    String comment;
    String author;
    String organization;

    public Comment(String comment, String author, String organization){
        this.comment = comment;
        this.author = author;
        this.organization = organization;
    }
    public String getComment(){
        return this.comment;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getOrganization(){
        return this.organization;
    }
}

/** Servlet that returns a comment */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private List< Comment > comments;

  public void getComments(){
    comments = new ArrayList<>();
    //Each comment map contains comment, author and Organization.
    Comment firstComment = new Comment("Comment 1, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.", "Author 1", "Organization 1" );
    comments.add(firstComment);

    Comment secondComment = new Comment("Comment 2, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.", "Author 2", "Organization 2");
    comments.add(secondComment);

    Comment thirdComment = new Comment("Comment 3, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.", "Author 1", "Organization 1");
    comments.add(thirdComment);

  }
  @Override
  public void init() {
      getComments();
    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = convertToJson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  //Converts a list of Comment instance into a JSON string using the Gson library.
  private String convertToJson(List< Comment > comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }

}
