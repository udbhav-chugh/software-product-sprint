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

    (function ($) {
    "use strict"; // Start of use strict

    // Scrolling using jQuery
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
        if (
            location.pathname.replace(/^\//, "") ==
                this.pathname.replace(/^\//, "") &&
            location.hostname == this.hostname
        ) {
            var target = $(this.hash);
            target = target.length
                ? target
                : $("[name=" + this.hash.slice(1) + "]");
            if (target.length) {
                $("html, body").animate(
                    {
                        scrollTop: target.offset().top - 72,
                    },
                    1000,
                    "easeInOutExpo"
                );
                return false;
            }
        }
    });

    // Closes responsive menu when a scroll trigger link is clicked
    $(".js-scroll-trigger").click(function () {
        $(".navbar-collapse").collapse("hide");
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $("body").scrollspy({
        target: "#mainNav",
        offset: 74,
    });

    // Collapse Navbar
    var navbarCollapse = function () {
        if ($("#mainNav").offset().top > 100) {
            $("#mainNav").addClass("navbar-shrink");
        } else {
            $("#mainNav").removeClass("navbar-shrink");
        }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);
})(jQuery); // End of use strict

/*Structure of each comment implemented in the for loop below:
<div class="commentbackground">
    <blockquote class="blockquote mb-0">
        <p>Comment</p>
        <footer class="blockquote-footer">Author, <i>Organization</i></footer>
    </blockquote>
</div>
<br>
*/

function getCommentPara(comment){
    var commentPara = document.createElement("p");
    var commentText = document.createTextNode(comment.comment);
    commentPara.appendChild(commentText);
    return commentPara;
}

function getCommentFooter(comment){
    var authorText = document.createTextNode(comment.author + ", ");
    var organizationText = document.createTextNode(comment.organization);
    var italicsOrganizationText = document.createElement("i");
    italicsOrganizationText.appendChild(organizationText);

    var commentFooter = document.createElement("footer");
    commentFooter.className = "blockquote-footer";
    commentFooter.appendChild(authorText);
    commentFooter.appendChild(italicsOrganizationText);
    return commentFooter;
}

function getCommentData(comment){
    var blockQuote = document.createElement("blockquote");
    blockQuote.className = "blockquote mb-0";
    blockQuote.appendChild(getCommentPara(comment));
    blockQuote.appendChild(getCommentFooter(comment));
    return blockQuote;
}

function getCommentDiv(comment){
    var commentDiv = document.createElement("div");
    commentDiv.className = "commentbackground";
    commentDiv.appendChild(getCommentData(comment));    
    return commentDiv;
}

function addCommentsToPortfolio() {

  fetch('/data').then(response => response.json()).then((comments) => {

    var commentsContainer = document.getElementById('comments-container');
    for(var i = 0; i < comments.length; i++){
        var commentDiv = getCommentDiv(comments[i]);
        var lineBreak = document.createElement("br");

        commentsContainer.appendChild(commentDiv);
        commentsContainer.appendChild(lineBreak);
    }

  });
}