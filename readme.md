# CS-492-Final-Project
Repository for CS 492 Final Project

**Introduction**
The purpose of this project is to build a mobile application that allows users to get accurate movie information efficiently. The app will have a homepage that will allow the users to see the movies that are trending. Also, It will allow users to select a genre of movies to be displayed that will be shown based on their popularity. Furthermore, the
users can search for some movies based on keywords. The user can select a movie to be shown more details about the film. Finally, the user can watch a trailer for the movie directly in the app through either an embedded Youtube Video or andriod native Media Player on YouTube via the detail activity for the movie.

**Current Mobile Development Mock:**
- https://www.figma.com/file/QDgD08IBMboW5ZM7dXL8kB/Mobile-development-mocks?node-id=0%3A1

**Structure:**
- **Home Page:** The main activity that displays movies in trend, list of newest movies.
- **Navigation Bar:** It will always display on the bottom of all activities. 
- **Search:** After users submit the uery keywords, the results will be displayed below the search bar
- **Genres:** Users can view a list of movies by selecting different genres
- **Detail View:** When users click on a movie, it will navigate to a movie detail page that
shows information about the specific movie

**Assets:**
- The Movie Database API https://developers.themoviedb.org/3/getting-started/introduction
- MediaPlayer https://developer.android.com/guide/topics/media/mediaplayer
- YouTube Android Player API https://developers.google.com/youtube/android/player

**Movie Database API Usage:**
- GET /genre/movie/list
- GET /discover/movie
- GET /movie/{movie_id}
- GET /movie/{movie_id}/videos
- GET /movie/{movie_id}/credits
- GET /keyword/{keyword_id}/movies

**MediaPlayer or Youtube Android Player API usage:**
-Per instructor: _"Using an implicit intent to launch YouTube or Chrome wouldn't be enough to satisfy the extra feature requirement, since we covered implicit intents in class.  However, if you had a player embedded right in the app, that would be perfect"._ 
-Need to use something like mediaPlayer or Youtube Andriod Player API
