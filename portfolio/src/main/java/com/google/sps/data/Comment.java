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

package com.google.sps.data;

public class Comment{
    private String comment;
    private String author;
    private String organization;

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