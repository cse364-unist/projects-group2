# projects-group2
projects-group2 created by GitHub Classroom

# run test
```
mvn clean test jacoco:report
```
# run the application
```
docker build -t group2-milestone2 .
docker run -e USERNAME=<username> -e PASSWORD=<password> --network host group2-milestone2
```


# API Documentations Below


### Description about the endpoint path

The path for the util section is /util and the path for the tag list is /taglist, so to access the tag list, you need to send a request to /util/taglist.


# Auth

Endpoints under the path “/auth”

We will use the session in the cookie.


## Sign up


### request

method: POST

path: /signup

args: (none)

data(body):
```
{

	“userId”: “wilson”,

	“password1”: “someSecretPW”, 

	“password2”: “someSecretPW”

}
```

### response
```
{

	// empty

}
```
### example


```
curl -X 'POST' \
  'http://localhost:8080/auth/signup' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userId": "myId",
  "password1": "myPassword",
  "password2": "myPassword"
}'
```


## Sign In


### request

method: POST

path: /signin

args: (none)
```
{

	“userId”: “wilson”,

	“password1”: “someSecretPW”, 

}
```

### response
```
{

	“session”: “someRandomStringForSession” // put this in “Cookie:” header of every request (ex: Cookie: session=someRandomStringForSession ) 

}

```
### example

```
curl -X 'POST' \
  'http://localhost:8080/auth/signin' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userId": "myId",
  "password": "myPassword"
}'
```


## Sign Out

It will invalidate the session.

There should be the “session” in the cookie header of the request, otherwise, it will throw an error.


### request

method: GET

path: /signout

Session is required.

args: (none)


### response
```
{

	// empty

}
```

### example

```
curl -X 'GET' \
  'http://localhost:8080/auth/signout' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>'
```


# Util

the endpoint under the “/util” 

they are not matches 1 to 1 (1 endpoint per 1 page), they will be needed when implementing the frontend.


## Tag List

get all tag list.

required for the autocomplete in searching keyword.


### request

method: GET

path: /taglist

args: (none)
```
```
### response
```
{

	“tagList”:[“bright”, “dark”, “sorrow”, “happy”, “sad”, “mad”] // all tags

}
```

### example

```
curl -X 'GET' \
  'http://localhost:8080/util/taglist' \
  -H 'accept: */*'
```


## Add Favorite


### request

method: POST

path: /addfavorite

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/util/addfavorite' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


## Remove Favorite


### request

method: POST

path: /removefavorite

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/util/removefavorite' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


# Feature 1: Recommend and Search Famous Line Based on Tag

The endpoint under the “/lines”


## Endpoint 1: Search Famous Line

You can get a list of famous lines from this endpoint.

However, you also can use this for searching and listing.

Also, it will recommend the famous line based on the favorite count.

It will just return all famous lines when you don’t send any argument.

And you have to sign in to use this endpoint.


### request

method: GET

path: /search

Session is required.

args:



* tags: string[]  // nullable. length:0~3
* favorite: bool //?favorite:  [true, 1, yes] are interpreted as true and [false, 0, no]are interpreted as false in Spring


### response
```
{

	“lines”:[

	{

		“id”: 1 // unique id of the line

		“actor”: “이병헌”

		“line”: “famous line”,

		“movie”: “the movie title”,

		“tag”: [“happy”, “sad”, “mad”], // up to 3.

		“favoriteCount”: 10 // int


    	“isFavorite”: false // is this line favorite of the user


    } 


    ]

}
```

### example

```
curl -X 'GET' \
  'http://localhost:8080/lines/search?tags=무서운&favorite=false' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>'
```


## Endpoint 2, 3: Add/Remove Favorite

They are under the “/util”. please visit the Util section.


## Endpoint 4: Get Tag List for the Autocomplete

Also under the “/util” section.


# feature 2: Upload and Vote the Famous Line

Endpoint under the path “/myLine” 


## Endpoint 1: Upload Famous Line

Users can upload famous lines to the famous line candidate list here.

movie and tags will be automatically generated if not exists.


### request

method: post

path: /upload

args: (none)

data(body):
```
{

	“line”: “famous line that user want to upload”,

	“title”: “the movie title”,

	“actor”: “이병헌”,

	“tags”: [“happy”, “sad”, “mad”],

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/myline/upload' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "line": "a Famous Line",
  "title": "movie title",
  "actor": "actor name",
  "tags": [
    "tag1", "tag2"
  ]
}'
```


## Endpoint 2: Voting Board

List up the famous line candidate with their expiration time so the users can vote for the famous line that they like.


### request

method: GET

path: /board

Session is required.

args: (none)


### response
```
{

	“lines”:[

	{

		“id”: 1 // unique id of the line

		“line”: “famous line”,

		“movie”: “the movie title”,

		“tags”: [“happy”, “sad”, “mad”], // up to 3.

		“expireAt”: “2024-04-26T15:45:00”, //yyyy-MM-dd HH:mm:ss


    	“like”: 14, // like count


    	“dislike”: 3 // dislike count


    	“isLike”: false // did the user liked this line


    	“isDislike”: true// did the user disliked this line


    } 


    ]

}
```

### example

```
curl -X 'GET' \
  'http://localhost:8080/myline/board' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>'
```


## Endpoint 3: Like

Like the line.

If the user had disliked the line, automatically cancels the dislike before like the line.


### request

method: POST

path: /like

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/myline/like' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


## Endpoint 4: Cancel Like

cancel the like


### request

method: POST

path: /unlike

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/myline/unlike' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


## Endpoint 5: Dislike


### request

method: POST

path: /dislike

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/myline/dislike' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


## Endpoint 6: Cancel Dislike

cancel dislike


### request

method: POST

path: /undislike

Session is required.

args: (none)

data(body):
```
{

	id: 1 // id of the line

}
```

### response
```
{

	// empty

}
```

### example

```
curl -X 'POST' \
  'http://localhost:8080/myline/undislike' \
  -H 'accept: */*' \
  -H 'Cookie: session=<yourSessionHere>' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1
}'
```


# Feature 3: Play Game Using the Movie Data

Endpoint under the “/game”


## Fetch Data for Quiz(guess title from the line)

You can fetch a random quiz data list.


### request

method: GET

path: /titlefromline/problems

args:



* count: int // how many quiz will you get


### response
```
{

	“data”:[


    {


        “title”: “movie title”, //string

			“line”: “famous line in the movie” //string

		}

	]

}
```

### example

```
curl -X 'GET' \
  'http://localhost:8080/game/titlefromline/problems?count=10' \
  -H 'accept: */*'
```

## Fetch Data for Quiz(guess actor from the line)

You can fetch a random quiz data list. (another type of quizes)

### request

method: GET

path: /actorfromline/problems

args:



* count: int // how many quiz will you get


### response
```
{

	“data”:[


    {


        “actor”: “이병헌”, //string

			“line”: “famous line in the movie” //string

		}

	]

}
```


### example

```
curl -X 'GET' \
  'http://localhost:8080/game/actorfromline/problems?count=10' \
  -H 'accept: */*'
```

