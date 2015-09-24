# UNIBULL

Repo for our ucsc independent study class project, unibull, which is an app that will help guide students in their college experience. Planned features: Classrooms, Event/News Bulletin, Food/Transportation, Clubs, and more!

##Intro:

Welcome to the github page for our UCSC Independent Study project, UniBull!

The idea is for UniBull to be an online hub local to your university, where you can create forums for your school's classes and clubs to discuss homework problems, create group study guides, find partners for group projects, or just talk about similar interests.

There will also be an online bulletin board where you can view flyers and ads that other students have posted, whether they are flyers for upcoming guest speakers and lectures, local concerts and shows, ads for tutoring or job opportunities, and more. For UCSC specifically, we have also added a place to view the dining hall menus for each day, where you can rate and comment on different items at specific dining halls.

## Setup
### CLJS

To get an interactive development environment run:

    rlwrap lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).

To create a production build run:

    lein cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

### CLJ
<cmdl>? lein repl

[repl]? (reset)

## License

Copyright © 2015 All Rights Reserved
