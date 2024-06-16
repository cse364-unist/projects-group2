# projects-group2
projects-group2 created by GitHub Classroom

# For milestone 3

---

## 1. Run the application

In your downloaded directory,
```
docker build -t group2-milestone3 .
```

And after that, type below line. In this case, you should type your github ID and your personel access token, because the program will be implemented in docker container not in your local machine.
```
docker run -e USERNAME=<username> -e ACCESS_TOKEN=<access_token> -p 8080:8080 -p 3000:3000 group2-milestone3
```

After few minutes, Environment settings will be finished. Then,
because we already linked our local machine and docker container,
you just open your web browser and type
```
http://localhost:8080/
```

Now you are in our web application.

---
## 2. How to Enjoy it

You can see the main page with home, search bar, and login button on the top of the web page.

for the full enjoying the program, you should sign up.
##### Login
1. Click the top right button "Login".
2. Click the center letter button "Don't have an account? Sign up".
3. Fill in the blank and Sign up.
4. Log in.

##### Feature 1 - Search by Tag
You can search any quotes by Tag(adjective).
For example, let's type "멋있는" in the search bar and press enter.
You can see many "멋있는" quotes and their related information.
For Each card, you can click "좋아요" button.

As you know, there is no member in our program. The only user is you. But for checking the sorting feature,

Let's click like button in "니가 가라 하와이." 
Then go to home by clicking "Home" top left button.
And go to search page by typing "멋있는" on the search bar again.
Finally you can see the quote you liked on the top place!
##### Feature 2 - Quote Voting Board
Let's click the "명대사 게시판" button bottom of the "Welcome to Movie Quotes".
In this place, you can upload your own favorite quote.
For checking the feature,

Let's type "소프트웨어 공학", "교수님", "김미정", "최고, 존엄"  sequentially, then click the Upload button.
Then you can see the uploaded card. In the card, with related information, you can also vote by clicking "like" and "dislike".

##### Feature 3 - Quote Game
Let's go to home and click the second or thire button at the center.
You can play quote-actor or quote-title match game.
If you click the button, then the game will start.

See the quote and type the related actor or movie title. The time limitation is set to 30s. 

---

##### For milestone 2 ...

You can find the detailed API documentation in the readme.md at milestone2 branch.
```
git checkout milestone2
```
