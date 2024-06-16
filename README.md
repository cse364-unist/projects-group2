# projects-group2
projects-group2 created by GitHub Classroom

# run the application (2 options)

1.
```
docker build -t group2-milestone3 .
docker run -p 8080:8080 group2-milestone3
```

2.
```
docker build -t group2-milestone3 .
docker run -e USERNAME=<username> -e ACCESS_TOKEN=<access_token> -p 8080:8080 -p 3000:3000 group2-milestone3
```
사용자 이름과 토큰은 Github username과 access_token을 넣어주시고, 실행해주시면 됩니다.
