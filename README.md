### 구성
    openjdk 11
    react
    springboot
    
### 빌드
    docker build : ./mvnw clean install jib:dockerBuild -Dimage=spring-react-fullstack:v1
    docker push  : ./mvnw clean install jib:build -Djib.to.image=deneb1593/spring-react-fullstack:v1

### 실행
    docker run --name fullstack -p 8080:8080 spring-react-fullstack:v1