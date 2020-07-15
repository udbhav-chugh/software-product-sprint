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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a comment */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private List<String> comments;

  @Override
  public void init() {
    comments = new ArrayList<>();
    comments.add("Commet 1, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    comments.add("Commet 2, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    comments.add("Commet 3, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at sodales tellus, nec scelerisque felis. Nam id lobortis leo. Praesent molestie neque a lacus vehicula iaculis. Curabitur pulvinar augue vel lacus vulputate, sit amet euismod turpis malesuada. Mauris cursus rhoncus mauris, sed tristique purus semper in.");
    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String comment = comments.get((int) (Math.random() * comments.size()));

    response.setContentType("text/html;");
    response.getWriter().println(comment);
  }
}
